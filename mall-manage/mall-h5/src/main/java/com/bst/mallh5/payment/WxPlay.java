package com.bst.mallh5.payment;


import com.alibaba.fastjson.JSONObject;
import com.bst.common.entity.order.OrderDetail;
import com.bst.common.entity.order.Orders;
import com.bst.common.entity.pay.OrderPayRecord;
import com.bst.common.mapper.pay.OrderPayRecordMapper;
import com.bst.common.modle.Result;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.CusAccessObjectUtil;
import com.bst.mallh5.service.orders.OrderService;
import com.bst.mallh5.utils.CommondUtil;
import com.bst.mallh5.utils.Signature;
import com.bst.mallh5.utils.Util;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 18-09-22 微信支付接口
 */
@Controller
@RequestMapping("/wxpay")
public class WxPlay {
    @Resource
    private OrderService orderService;

    @Autowired
    private OrderPayRecordMapper payRecordMapper;

    @Autowired
    private GoodsRedisService goodsRedisService;

    @Resource
    private Configure wxpayConfig;

    private Logger logger = LoggerFactory.getLogger(WxPlay.class);

    /**
     * @param request
     * @param orderNumber 订单号 totalFee 金额 以分单位
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pay(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String orderNumber) {
        logger.info("微信支付开始..." + orderNumber);
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        Map<String, Object> returnMap = new ConcurrentHashMap<String, Object>();
        returnMap.put("code", "-1");// 下单失败
        Orders order = orderService.queryOrdersByOrdersNo(orderNumber);
        if (null == order) {
            return null;
        }
        if (order.getPayStatus() == 2) {
            returnMap.put("code", "501");
            returnMap.put("msg", "订单已支付，请勿重复支付！");
            return returnMap;
        }
        Date timeoutDate = DateUtils.addHours(order.getCreateTime(), 2);
        if (System.currentTimeMillis() > timeoutDate.getTime() || order.getPayStatus() == 3) {
            returnMap.put("code", "502");
            returnMap.put("msg", "超时支付，订单自动取消！");// 下单失败
            return returnMap;
        }
        logger.info("订单校验通过.order:", order);
        int totalPrice = order.getTotalAmount().movePointRight(2).intValue();
        WxPlaceOrder wxPlaceOrder = new WxPlaceOrder();
        wxPlaceOrder.setAppid(wxpayConfig.getAppId());
        wxPlaceOrder.setBody("购买商品");
        wxPlaceOrder.setMch_id(wxpayConfig.getPayId());
        wxPlaceOrder.setNonce_str(Util.uuid());
        wxPlaceOrder.setNotify_url(wxpayConfig.getNotifyUrl());
        wxPlaceOrder.setOut_trade_no(orderNumber);
        String realIp = CusAccessObjectUtil.getIpAddress(request);
        System.out.println("real-ip ------------------->" + realIp);
        wxPlaceOrder.setSpbill_create_ip(realIp);
        wxPlaceOrder.setTotal_fee(totalPrice);
//        wxPlaceOrder.setTotal_fee(1);
        wxPlaceOrder.setTrade_type("MWEB");
        wxPlaceOrder.setScene_info(sceneInfoJson(request));
        wxPlaceOrder.setSign(Signature.getSign(wxPlaceOrder));
        XStream xStream = new XStream(new StaxDriver(new NoNameCoder()));
        xStream.alias("xml", WxPlaceOrder.class);
        String xml = xStream.toXML(wxPlaceOrder);
        logger.info("请求微信支付...xml参数:" + xml);
        String result = CommondUtil.httpsRequest(wxpayConfig.getPlaceOrderUrl(),
                "POST", xml);
        logger.info("微信支付请求响应...result:" + result);

        if (!"".equals(result)) {
            try {
                map = XMLParser.getMapFromXML(result);
                if ("SUCCESS".equals(map.get("return_code"))) {
                    returnMap.put("code", "0");// 下单成功
                    String mweb_url = (String) map.get("mweb_url");
                    returnMap.put("redirect", mweb_url);
                } else {
                    returnMap.put("msg", map.get("return_msg"));
                }
            } catch (Exception e) {
                logger.error("统一下单异常", e);
            }
        }
        logger.info("微信支付结束");
        return returnMap;
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public void notity(HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        logger.info("微信支付回调开始...");
        Map<String, String> map = parseXml(request);
        WxPlaceOrder wxPlaceOrder = new WxPlaceOrder();
        logger.info("微信支付回调信息...result:" + map);
        // 当前订单的通知业务
        if ("SUCCESS".equals(map.get("return_code"))) {
            String out = map.get("out_trade_no");
            String paymentSeq = map.get("transaction_id");
            String time_end = map.get("time_end");

            // 支付交易成功
            logger.info(out);
            // 插入支付记录
            Orders order = orderService.queryOrdersByOrdersNo(out);
            insertPayResult(order, paymentSeq, time_end);

            // 通知成功
            wxPlaceOrder.setReturn_code("SUCCESS");
            XStream xStream = new XStream(new StaxDriver(new NoNameCoder()));
            xStream.alias("xml", WxPlaceOrder.class);
            String returnXml = xStream.toXML(wxPlaceOrder);
            try {
                response.getWriter().write(returnXml);
            } catch (IOException e) {
                logger.error("通知回调 微信服务器 异常", e);
            }
        }
        logger.info("微信支付回调结束...");
    }

    @RequestMapping(value = "/checked", method = RequestMethod.POST)
    @ResponseBody
    public Result checkedPayStatus(@RequestBody String orderNo) {
        String msg = "等待支付结果...";
        Orders order = orderService.queryOrdersByOrdersNo(orderNo);
        byte payStatus = order.getPayStatus();
        if (payStatus == 2) {
            return Result.ok("支付成功！", null);
        }
        WxCheckedPay checkedPay = new WxCheckedPay();
        checkedPay.setAppid(wxpayConfig.getAppId());
        checkedPay.setMch_id(wxpayConfig.getPayId());
        checkedPay.setOut_trade_no(orderNo);
        checkedPay.setNonce_str(Util.uuid());
        checkedPay.setSign(Signature.getSign(checkedPay));
        XStream xStream = new XStream(new StaxDriver(new NoNameCoder()));
        xStream.alias("xml", WxCheckedPay.class);
        String xml = xStream.toXML(checkedPay);
        logger.info("查询微信支付结果开始...xml参数:" + xml);
        String result = CommondUtil.httpsRequest(wxpayConfig.getTrackOrderUrl(),
                "POST", xml);
        logger.info("查询微信支付结果响应...result:" + result);
        if ("".equals(result)) {
            return Result.error("QUERY FAILED!");
        }
        Map<String, Object> map;
        String successFlag = "SUCCESS";
        try {
            map = XMLParser.getMapFromXML(result);
            if (successFlag.equals(map.get("return_code"))
                    && successFlag.equals(map.get("result_code"))
                    && successFlag.equals(map.get("trade_state"))) {
                String paymentSeq = String.valueOf(map.get("transaction_id"));
                String time_end = String.valueOf(map.get("time_end"));
                insertPayResult(order, paymentSeq, time_end);
                return Result.ok("支付成功！", null);
            }
            if (successFlag.equals(map.get("result_code"))) {
                if ("NOTPAY".equals(map.get("trade_state"))) {
                    msg = "支付未完成，请重新支付或咨询客服.";
                }
            } else {
                if ("ORDERNOTEXIST".equals(map.get("err_code"))) {
                    msg = "订单未支付.";
                }
            }
        } catch (Exception e) {
            logger.error("查询异常", e);
            msg = "QUERY ERROR！";
        }
        return Result.error(msg);
    }

    private Map<String, String> parseXml(HttpServletRequest request)
            throws Exception, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(request.getInputStream());
        // 获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, String> map = new HashMap<String, String>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }

    /**
     * 获取环境信息
     *
     * @param request
     * @return
     */
    private String sceneInfoJson(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "Wap");
        jsonObject.put("app_name", "安全教育平台");

        jsonObject.put("wap_url", request.getRequestURL().toString());
        jsonObject.put("wap_name", "安全教育");

        JSONObject sceneInfo = new JSONObject();
        sceneInfo.put("h5_info", jsonObject);
        return sceneInfo.toJSONString();
    }

    /**
     * 插入支付成功结果
     *
     * @param order      订单对象
     * @param paymentSeq 微信订单号
     * @param time_end   支付完成时间
     * @throws ParseException 时间格式转化异常
     */
    private void insertPayResult(Orders order, String paymentSeq, String time_end) throws ParseException {
        OrderPayRecord payRecord = new OrderPayRecord();
        payRecord.setOrderId(order.getId());
        payRecord.setPayNo(paymentSeq);
        payRecord.setPayType((byte) 1);
        payRecord.setPayAmount(order.getTotalAmount());
        payRecord.setPayCreate(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date timeEnd = sdf.parse(time_end);
        payRecord.setFinishTime(timeEnd);
        payRecord.setPayStatus((byte) 2);
        payRecordMapper.insert(payRecord);
        long count = orderService.updateOrderFinishPay(order.getOrderNo(), (byte) 1, (byte) 1);
        if (count < 1) {
            count = orderService.updateOrderFinishPay(order.getOrderNo(), (byte) 1, (byte) 3);
            if (count > 0) {
                // 从取消订单状态更新到已支付，更新成功，说明系统自动超时处理，需要扣减回退的库存
                OrderDetail detail = orderService.getOrderDetailByOrderId(order.getId());
                goodsRedisService.delCountBySpuNO(detail.getSpuNo(), order.getGoodsCount());
                goodsRedisService.delCountBySkuNO(detail.getSkuNo(), order.getGoodsCount());
            }
        }
    }

}
