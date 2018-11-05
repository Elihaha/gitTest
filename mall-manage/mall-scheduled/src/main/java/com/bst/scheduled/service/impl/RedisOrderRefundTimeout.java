package com.bst.scheduled.service.impl;

import com.bst.common.constants.ReturnOfGoodsConstants;
import com.bst.common.modle.order.OrderDetailMonitoringInfo;
import com.bst.common.service.GoodsRedisService;
import com.bst.order.service.OrderDetailService;
import com.bst.order.service.OrdersManageService;
import com.bst.scheduled.service.AbstractRedisTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @description RefundTimeout 退款超时
 */
@Component
public class RedisOrderRefundTimeout extends AbstractRedisTask {

    @Autowired
    private OrdersManageService ordersService;


    private String orderRefundTimeout;


//    @Value("redisKey.config.order_timeout-delay")
//    private String ORDER_TIMEOUT_DELAY_KEY;

    //消费者，取订单


    @Override
    protected void logic(String orderNo) {
        //商品数量
        ordersService.updateReturnStatus(orderNo, ReturnOfGoodsConstants.SYSTEM_REFUND);
    }

    @Override
    protected void getMessage(String orderNo) {
        System.out.println(System.currentTimeMillis() + "订单过期" + orderNo);
    }

    @Override
    protected String getKey() {
        return orderRefundTimeout;
    }


    @Override
    public void init() {
        orderRefundTimeout = redisParam.getOrderTimeoutDelayKey();
        lowSize = jedisCluster.zcard(orderRefundTimeout);
        if (lowSize==0) {
            lowSize=1L;
        }
    }


}
