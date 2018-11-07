package com.bst.mallh5.service.orders.impl;

import com.alibaba.fastjson.JSON;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.entity.order.*;
import com.bst.common.entity.shop.ShippingAddress;
import com.bst.common.entity.shop.ShopInfo;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.mapper.order.*;
import com.bst.common.mapper.shop.ShippingAddressMapper;
import com.bst.common.mapper.shop.ShopInfoMapper;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsPicNameQuery;
import com.bst.common.modle.orders.DetailResult;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.service.RedisOrderService;
import com.bst.common.service.impl.RedisOrderServiceImpl;
import com.bst.common.utils.OrderNoUtil;
import com.bst.mallh5.model.orders.OrdersCreatePOJO;
import com.bst.mallh5.service.orders.OrderService;
import com.bst.mallh5.service.orders.model.ShippingAddressRequest;
import com.bst.mallh5.user.PlatformUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.*;

/**
 * 订单业务层实现
 *
 * @author zouqiang
 * @create 2018-09-17 17:35
 **/
@Service
@Primary
@Slf4j
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,rollbackFor = Throwable.class,readOnly = false)
public class OrderCreateService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderCreateService.class);

    @Autowired
    private GoodsRedisService goodsRedisService;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private GoodsSpuMapper goodsSpuMapper;
    @Autowired
    private  OrderService orderService;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    @Autowired
    private RedisOrderServiceImpl redisOrderService;
    /*
        创建主订单
     * @author zouqiang
     * @date 2018/9/18 15:19
     *
     */

    public Result createOrder(String goodsSkuNo, int count,  ShippingAddressRequest shippingAddressRequest) {
        //  手动回滚
        String rollbackSpu = null;
        String rollbackSku = null;
        try {
            // 订单实体
            Orders nOrders = new Orders();
            //通过SpuId查询spu表
            GoodsSku goodsSku = goodsSkuMapper.selectSkuBySkuNo(goodsSkuNo);
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(goodsSku.getSpuId());
            //  更新  spu 库存
            String spuNo= goodsSpu.getSpuNo();
            log.info(" 初始的  数量 {}",goodsRedisService.getCurrentSPuCountBySpuNO(spuNo));
            boolean sax = orderService.updateSpuStock(spuNo, count);
            boolean sax2 = orderService.updateSkuStock( goodsSku.getSkuNo() , count);
            if (!sax||!sax2) {
                return Result.error("超过库存，下订单失败");
            }
//            如果rollback 是  true 了就 接下来需要回滚redis了
            rollbackSpu = spuNo;
            rollbackSku = goodsSku.getSkuNo();
//            //  测试  回滚  redis 数据
//             if(rollback!=null){
//                 throw  new RuntimeException("测试回滚");
//             }
            //通过SpuId查询Sku表
            //
            ShippingAddress shippingAddress = new ShippingAddress();
            //   生成 订单数据
            OrdersCreatePOJO build = OrdersCreatePOJO.builder()
                    .count(count)
                    .province(shippingAddressRequest.getProvince())
                    .nOrders(nOrders)
                    .sellPrice(goodsSku.getSellPrice())
                    .shopId(goodsSpu.getShopId())
                    .signerPhone(shippingAddressRequest.getSignerPhone())
                    .build();
            orderService.beforeInsertOrder(build);
            //nOrders插入订单表
            int num = ordersMapper.insertSelective(nOrders);
//        //更新库存
            //查询出图片，商品，价格
            GoodsPicNameQuery goodsPicNameQuery = goodsSpuMapper.queryPicAndName(goodsSku.getSkuNo());
            /**>>>>>>>>>>>>>>>>>>>>>>>
            goodsPicNameQuery.setPecValue();
            <<<<<<<<<<<<<<<<<<<<<<<<<<<<<***/
            goodsPicNameQuery.setPecValue("红-大-i5  写死的规格");
            String orderDetails = JSON.toJSONString(goodsPicNameQuery);
            //默认付款类型
            int payType = 1;
            //创建订单详情表
            orderService.createOrderDetail(nOrders.getId(), nOrders.getOrderNo(), rollbackSpu, rollbackSku, orderDetails);

            shippingAddress.setOrderdId( nOrders.getId());
            BeanUtils.copyProperties(shippingAddressRequest,shippingAddress);
//            shippingAddress.setSigner(shippingAddressRequest.getSigner());
//            shippingAddress.setSignerPhone(shippingAddressRequest.getSignerPhone());
//            shippingAddress.setProvince(shippingAddressRequest.getProvince());
//            shippingAddress.setCity(shippingAddressRequest.getCity());
//            shippingAddress.setArea(shippingAddressRequest.getArea());
//            shippingAddress.setAddress(shippingAddressRequest.getAddress());
//            shippingAddress.setPostcode(shippingAddressRequest.getPostcode());
            //创建收货地址
            shippingAddressMapper.insert(shippingAddress);
            redisOrderService.productionDelayMessage(nOrders.getOrderNo());
            return Result.ok(200, "创建成功", nOrders);
        } catch (Exception e) {
            if (StringUtils.isNoneBlank(rollbackSpu,rollbackSku)) {
                goodsRedisService.addCountBySpuNO(rollbackSpu, count);
                log.info(" 测试结果数量  数量 {}",goodsRedisService.getCurrentSPuCountBySpuNO(rollbackSpu));
                goodsRedisService.addCountBySkuNO(rollbackSku, count);
                log.info(" 测试结果数量  数量 {}",goodsRedisService.getCurrentSkuCountBySkuNO(rollbackSku));
            }
            log.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
//            return Result.error(e.getMessage());
        }
        return Result.error("  hahahah");
    }




}
