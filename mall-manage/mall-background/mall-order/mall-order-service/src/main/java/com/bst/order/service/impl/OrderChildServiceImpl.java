package com.bst.order.service.impl;

import com.bst.common.entity.order.OrderChild;
import com.bst.common.mapper.order.OrderChildMapper;
import com.bst.order.service.OrderChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("orderChildService")
@Transactional(value = "mallTransactionManager" ,readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
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
