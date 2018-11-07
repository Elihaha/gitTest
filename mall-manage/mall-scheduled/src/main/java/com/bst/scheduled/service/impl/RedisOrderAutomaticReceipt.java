package com.bst.scheduled.service.impl;

import com.bst.common.constants.OrdersConstants;
import com.bst.common.modle.order.OrderDetailMonitoringInfo;
import com.bst.common.service.GoodsRedisService;
import com.bst.order.service.OrderDetailService;
import com.bst.order.service.OrdersManageService;
import com.bst.scheduled.service.AbstractRedisTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * @description 自动 收货
 */
@Component
public class RedisOrderAutomaticReceipt extends AbstractRedisTask {

    @Autowired
    private OrdersManageService ordersManageService;

    private String orderAutomaticReceipt;


//    @Value("redisKey.config.order_timeout-delay")
//    private String ORDER_TIMEOUT_DELAY_KEY;

    //消费者，取订单


    @Override
    protected void logic(String orderNo) {
        //商品数量
        ordersManageService.updateOrderStatus(orderNo, OrdersConstants.RECEIVED,OrdersConstants.PAID);
    }

    @Override
    protected void getMessage(String orderNo) {
        System.out.println(System.currentTimeMillis() + "自动签收" + orderNo);
    }

    @Override
    protected String getKey() {
        return orderAutomaticReceipt;
    }


    @Override
    public void init() {
        orderAutomaticReceipt = redisParam.getOrderTimeoutDelayKey();
        lowSize = jedisCluster.zcard(orderAutomaticReceipt);
        if (lowSize==0) {
            lowSize=1L;
        }
    }


    public void add(String value) {
        final long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()+addDateTime(7);
        super.add(l, value);
    }
}
