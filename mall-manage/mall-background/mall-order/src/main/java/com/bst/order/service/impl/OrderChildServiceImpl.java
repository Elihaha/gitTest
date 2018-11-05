package com.bst.order.service.impl;

import com.bst.common.entity.order.OrderChild;
import com.bst.common.mapper.order.OrderChildMapper;
import com.bst.order.service.OrderChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderChildService")
public class OrderChildServiceImpl implements OrderChildService {
    @Autowired
    private OrderChildMapper orderChildMapper;

    @Override
    public OrderChild selectByOrderNo(String orderNo) {
        return orderChildMapper.selectByOrderNo(orderNo);
    }

    @Override
    public void updateByPrimaryKeySelective(OrderChild orderChild) {
        orderChildMapper.updateByPrimaryKeySelective(orderChild);
    }
}
