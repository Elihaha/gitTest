package com.bst.scheduled.service.impl;

import com.bst.common.constants.OrdersConstants;
import com.bst.common.constants.ReturnOfGoodsConstants;
import com.bst.common.modle.order.OrderDetailMonitoringInfo;
import com.bst.common.service.GoodsRedisService;
import com.bst.order.service.OrderDetailService;
import com.bst.order.service.OrdersManageService;
import com.bst.scheduled.service.AbstractRedisTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @description 商家拒绝退货  用户操作超时处理
 */
@Component
public class RedisOrderRefuseReturnTimeout extends AbstractRedisTask {

    @Autowired
    private OrdersManageService ordersService;


    private String orderRefuseReturnTimeout;


//    @Value("redisKey.config.order_timeout-delay")
//    private String ORDER_TIMEOUT_DELAY_KEY;

    //消费者，取订单


    @Override
    protected void logic(String orderNo) {
        //商品数量
          ordersService.updateOrderReturnStatus(orderNo,OrdersConstants.CLOSE_THE_RETURN);
    }

    @Override
    protected void getMessage(String orderNo) {
        System.out.println(System.currentTimeMillis() + "订单过期" + orderNo);
    }

    @Override
    protected String getKey() {
        return orderRefuseReturnTimeout;
    }


    @Override
    public void init() {
        orderRefuseReturnTimeout = redisParam.getOrderTimeoutDelayKey();
        lowSize = jedisCluster.zcard(orderRefuseReturnTimeout);
        if (lowSize==0) {
            lowSize=1L;
        }
    }


}
