package com.bst.order.service;

import com.bst.common.entity.order.OrderChild;
import com.bst.common.entity.order.OrderRefund;

/**
 * @Auther: WangCheng
 * @Date: 2018/9/20 18:51
 * @Description: 订单退货
 */
public interface OrderRefundService {
    public void insert(OrderRefund orderRefund);
    /* @description：根据订单号orderNo查询订单退货表
         * @author WangCheng
         * @date 2018/9/20 20:34
         * @param
         * @return
         */
    public OrderRefund selectByOrderNo(String orderNo);

    void uploadById(OrderRefund orderRefund);

    void applySaleReturn(String orderNo,String reason);

    OrderChild agreeSaleReturn(String orderNo);
}
