package com.bst.order.controller;

import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.entity.order.*;
import com.bst.common.service.GoodsRedisService;
import com.bst.order.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bst.common.modle.Result;

import java.util.Date;

/**
 * @Auther: WangCheng
 * @Date: 2018/9/20 17:23
 * @Description: 退货
 */
@Log4j2
@RestController
@Api(value = "退货",tags={"退货"})
@RequestMapping("saleReturn")
public class OrderSalesRetutnController {
    @Autowired
    private  OrdersManageService ordersService;
    @Autowired
    private OrderChildService orderChildService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderRefundService orderRefundService;
    @Autowired
    private GoodsRedisService    goodsRedisService;

    /* @description：买家申请退货
     * @author WangCheng
     * @date 2018/9/20 17:30
     * @param 包含订单編号与退款原因的orderRefund
     * @return
     */
    @ApiOperation(value = "申请退货",notes = "子订单号OrderNo，退款原因", response = Result.class)
    @PostMapping("/applySaleReturn")
    public  Result applySaleReturn(@RequestParam("orderNo")String orderNo,@RequestParam("reason")String reason){
        //前台传订单号，退款原因，
        Result result = new Result();
        try {
            orderRefundService.applySaleReturn(orderNo,reason);
            result.setMsg("申请退货成功");
            result.setStatus(200);
            return result;
        }catch (Exception ex){
            log.error("apply return goods error.", ex);
            result.setMsg("申请退货失败");
            result.setStatus(500);
            return result;
        }
    }
    /* @description：同意退货
     * @author WangCheng
     * @date 2018/9/20 20:19
     * @param 订单编号
     * @return
     */
    @ApiOperation(value = "同意退货",notes = "子订单号OrderNo", response = Result.class)
    @GetMapping("/agreeSaleReturn/{orderNo}")
    public  Result agreeSaleReturn(@PathVariable("orderNo") String orderNo){
        Result result = new Result();
        try {
            OrderChild orderChild =orderRefundService.agreeSaleReturn(orderNo);
            //修改库存 //库存回滚
            Orders orders = ordersService.selectByOrderNo(orderNo);
            if (orders!=null){
                //商品数量
                int goodsCount =orderChild.getGoodsCount();
                OrderDetail orderDetail = orderDetailService.queryOrderDetail(orders.getId());
                if (orderDetail!=null){
                    goodsRedisService.addCountBySkuNO(orderDetail.getSkuNo(),goodsCount);
                    goodsRedisService.addCountBySpuNO(orderDetail.getSpuNo(),goodsCount);
                }
            }
            result.setMsg("同意退货成功");
            result.setStatus(200);
            return result;
        }catch (Exception ex) {
            result.setMsg("同意退货失败");
            result.setStatus(500);
            return result;
        }
    };
    /* @description：退货成功
     * @author WangCheng
     * @date 2018/9/20 20:59
     * @param 订单编号
     * @return
     */
    @ApiOperation(value = "已退货",notes = "子订单号OrderNo", response = Result.class)
    @GetMapping("/SaleReturnSuccess/{orderNo}")
    public Result SaleReturnSuccess(@PathVariable("orderNo") String orderNo){
        Result result = new Result();
        try {
            OrderChild orderChild = orderChildService.selectByOrderNo(orderNo);
            //已退货
            orderChild.setStatus(Status.SaleReturned);
            orderChild.setIsReturn(true);
            orderChildService.updateByPrimaryKeySelective(orderChild);
            OrderRefund orderRefund = orderRefundService.selectByOrderNo(orderNo);
            //确认时间
            orderRefund.setRefundTime(new Date());
            //退款状态 已退款/货
            orderRefund.setRefund(Status.SaleReturned);
            //操作人
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            orderRefund.setOperator(operator.getName());
            orderRefundService.uploadById(orderRefund);
            result.setMsg("退款成功");
            result.setStatus(200);
            return result;
        }catch (Exception ex){
            result.setMsg("退款失败");
            result.setStatus(500);
            return result;
        }
    }
    @ApiOperation(value = "退款",notes = "主订单表id", response = Result.class)
    @GetMapping("/ReturnGoods/{id}")
    public Result ReturnGoods(@PathVariable("id") long id){
        Result result= new Result();
        try {
            Orders orders = ordersService.selectByPrimaryKey(id);
            ordersService.ReturnGoods(orders);
            result.setMsg("退款成功");
            result.setStatus(200);
            return result;
        }catch (Exception e){
            result.setMsg("退款失败");
            result.setStatus(500);
            return result;
        }
    }
}
