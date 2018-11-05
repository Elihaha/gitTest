package com.bst.order.service;

import com.bst.common.entity.order.OrderChild;

/**
 * @Auther: WangCheng
 * @Date: 2018/9/20 18:19
 * @Description: 子订单
 */
public interface OrderChildService {
    public OrderChild selectByOrderNo(String orderNo);
    public void updateByPrimaryKeySelective(OrderChild orderChild);
}
