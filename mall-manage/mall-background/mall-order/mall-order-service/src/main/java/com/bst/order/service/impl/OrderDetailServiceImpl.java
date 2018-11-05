package com.bst.order.service.impl;

import com.bst.common.entity.order.OrderDetail;
import com.bst.common.entity.order.OrderDetailExample;
import com.bst.common.mapper.order.OrderDetailMapper;
import com.bst.common.modle.order.OrderDetailMonitoringInfo;
import com.bst.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单详情实现
 *
 * @author zouqiang
 * @create 2018-09-19 17:45
 **/
@Service
@Transactional(value = "mallTransactionManager" ,readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    //查询订单详情
    //前期只有一种商品，list中去第一个返回
    public OrderDetail queryOrderDetail(Long OrderId){
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(OrderId);
        List<OrderDetail> list = orderDetailMapper.selectByExample(example);
        OrderDetail orderDetail =  list.get(0);
        return orderDetail;
    }

    @Override
    public OrderDetailMonitoringInfo queryOrderDetailByOrderNo(String ordersNo) {
        return orderDetailMapper.queryOrderDetailByOrderNo(ordersNo);
    }
}
