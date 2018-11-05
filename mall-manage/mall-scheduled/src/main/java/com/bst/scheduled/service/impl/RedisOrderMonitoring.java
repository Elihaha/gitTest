package com.bst.scheduled.service.impl;

import com.bst.common.entity.order.OrderDetail;
import com.bst.common.entity.order.Orders;
import com.bst.common.modle.order.OrderDetailMonitoringInfo;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.order.service.OrderDetailService;
import com.bst.order.service.OrdersManageService;
import com.bst.scheduled.service.AbstractRedisTask;
import net.sf.jsqlparser.statement.select.Offset;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Tuple;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Set;


/**
 * @description 检测redis里面待支付的订单是否过期 并且进行处理
 */
@Component
public class RedisOrderMonitoring   extends AbstractRedisTask {

    @Autowired
    private OrdersManageService ordersService;
    @Autowired
    private GoodsRedisService goodsRedisService;
    @Autowired
    private OrderDetailService orderDetailService;

    private String orderTimeoutDelayKey;


//    @Value("redisKey.config.order_timeout-delay")
//    private String ORDER_TIMEOUT_DELAY_KEY;

    //消费者，取订单


    @Override
    protected void logic(String orderNo) {
        //商品数量
        long count = ordersService.orderTimeout(orderNo);
        if (count != 0) {
            //修改库存
            OrderDetailMonitoringInfo orderDetail = orderDetailService.queryOrderDetailByOrderNo(orderNo);
            if (orderDetail == null) {
                return;
            }
            int goodsCount = orderDetail.getGoodsCount();
            goodsRedisService.addCountBySkuNO(orderDetail.getSkuNo(), goodsCount);
            goodsRedisService.addCountBySpuNO(orderDetail.getSpuNo(), goodsCount);
        }
    }

    @Override
    protected void getMessage(String orderNo) {
        System.out.println(System.currentTimeMillis() + "订单过期" + orderNo);
    }

    @Override
    protected String getKey() {
        return orderTimeoutDelayKey;
    }


    @Override
    public void init() {
        orderTimeoutDelayKey = redisParam.getOrderTimeoutDelayKey();
        lowSize = jedisCluster.zcard(orderTimeoutDelayKey);
        if (lowSize==0) {
            lowSize=1L;
        }
    }


}
