package com.bst.scheduled.service.impl;

import com.bst.common.modle.order.OrderDetailMonitoringInfo;
import com.bst.common.service.GoodsRedisService;
import com.bst.order.service.OrderDetailService;
import com.bst.order.service.OrdersManageService;
import com.bst.scheduled.service.AbstractRedisTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @description 退款退货超时
 * @Deprecated    暂时取消该类 合并到
 */
//@Component
@Deprecated
public class RedisOrderRefundReturnTimeout extends AbstractRedisTask {

    @Autowired
    private OrdersManageService ordersService;


    private String orderRefundReturnTimeout;


//    @Value("redisKey.config.order_timeout-delay")
//    private String ORDER_TIMEOUT_DELAY_KEY;

    //消费者，取订单


    @Override
    protected void logic(String orderNo) {
        //商品数量

    }

    @Override
    protected void getMessage(String orderNo) {
        System.out.println(System.currentTimeMillis() + "订单过期" + orderNo);
    }

    @Override
    protected String getKey() {
        return orderRefundReturnTimeout;
    }


    @Override
    public void init() {
        orderRefundReturnTimeout = redisParam.getOrderTimeoutDelayKey();
        lowSize = jedisCluster.zcard(orderRefundReturnTimeout);
        if (lowSize==0) {
            lowSize=1L;
        }
    }


}
