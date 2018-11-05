package com.bst.order.service;

import com.bst.common.entity.order.OrderDetail;

/**
 * 订单详情
 *
 * @author zouqiang
 * @create 2018-09-19 17:43
 **/
public interface OrderDetailService {

    //查询订单详情
    public OrderDetail queryOrderDetail(Long OrdersId);
}
