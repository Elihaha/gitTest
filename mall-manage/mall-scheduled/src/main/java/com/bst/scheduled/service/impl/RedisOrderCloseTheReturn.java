package com.bst.scheduled.service.impl;

import com.bst.common.constants.OrdersConstants;
import com.bst.common.modle.order.OrderDetailMonitoringInfo;
import com.bst.common.service.GoodsRedisService;
import com.bst.order.service.OrderDetailService;
import com.bst.order.service.OrdersManageService;
import com.bst.scheduled.service.AbstractRedisTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @description
 * 2.商家发货七天后系统自动签收，若中途产生退款，7天倒计时
 *
 *   暂停直至退款关闭
 *
 * 3.已完成订单7天后退款入口关闭，如中途产生退款，7天倒计时
 *
 *    暂停直至退款关闭
 */

@Component(value = "orderCloseTheReturn")
public class RedisOrderCloseTheReturn extends AbstractRedisTask {

    @Autowired
    private OrdersManageService ordersService;

    private String orderCloseTheReturn;


//    @Value("redisKey.config.order_timeout-delay")
//    private String ORDER_TIMEOUT_DELAY_KEY;

    //消费者，取订单


    @Override
    protected void logic(String orderNo) {
        //商品数量
        ordersService.updateOrderReturnStatus(orderNo, OrdersConstants.CLOSE_THE_RETURN);
    }

    @Override
    protected void getMessage(String orderNo) {
        System.out.println(System.currentTimeMillis() + " 关闭 退货"  + orderNo);
    }

    @Override
    protected String getKey() {
        return orderCloseTheReturn;
    }


    @Override
    public void init() {
        orderCloseTheReturn = redisParam.getOrderTimeoutDelayKey();
        lowSize = jedisCluster.zcard(orderCloseTheReturn);
        if (lowSize==0) {
            lowSize=1L;
        }
    }


}
