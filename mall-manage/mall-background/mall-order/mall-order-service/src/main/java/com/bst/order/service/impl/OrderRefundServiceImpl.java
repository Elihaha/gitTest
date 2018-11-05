package com.bst.order.service.impl;

import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.entity.order.OrderChild;
import com.bst.common.entity.order.OrderRefund;
import com.bst.common.entity.order.Status;
import com.bst.common.mapper.order.OrderChildMapper;
import com.bst.common.mapper.order.OrderRefundMapper;
import com.bst.order.service.OrderRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service("orderRefundService")
@Transactional(value = "mallTransactionManager" ,readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class OrderRefundServiceImpl implements OrderRefundService {
    @Autowired
    private OrderRefundMapper orderRefundMapper;
    @Autowired
    private OrderChildMapper orderChildMapper;

    @Override
    public void insert(OrderRefund orderRefund) {
        orderRefundMapper.insertSelective(orderRefund);
    }

    @Override
    public OrderRefund selectByOrderNo(String orderNo) {
        return orderRefundMapper.selectByOrderNo(orderNo);
    }

    @Override
    public void uploadById(OrderRefund orderRefund) {
        orderRefundMapper.updateByPrimaryKey(orderRefund);
    }

    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public void applySaleReturn(String orderNo,String reason) {
        OrderChild orderChild = orderChildMapper.selectByOrderNo(orderNo);
        //未退货
        orderChild.setIsReturn(false);
        //申请退货时间
        Date date = new Date();
        orderChild.setSalesReturnTime(date);
        OrderRefund orderRefund = new OrderRefund();
        orderRefund.setOrderNo(orderNo);
        orderRefund.setRefundReason(reason);
        orderRefund.setApplyTime(date);
        //修改订单状态 申请退货
        orderChild.setStatus(Status.applySaleReturn);
        orderRefund.setRefund(Status.applySaleReturn);
        //退款金额
        orderRefund.setRefundAmount(orderChild.getPrice());
        orderRefundMapper.insert(orderRefund);
        orderChildMapper.updateByPrimaryKeySelective(orderChild);
    }

    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public OrderChild agreeSaleReturn(String orderNo) {
        OrderChild orderChild = orderChildMapper.selectByOrderNo(orderNo);
        //修改订单状态同意退货
        orderChild.setStatus(Status.SaleReturning);
        orderChildMapper.updateByPrimaryKeySelective(orderChild);
        OrderRefund orderRefund = orderRefundMapper.selectByOrderNo(orderNo);
        //操作人
        Operator operator = PermissionInfoUtil.getCurrentLogginUser();
        orderRefund.setOperator(operator.getName());
        //确认退货时间
        orderRefund.setReplyTime(new Date());
        //同意退款/货 ,退款中
        orderRefund.setRefund(Status.SaleReturning);
        orderRefundMapper.updateByPrimaryKey(orderRefund);
        return orderChild;
    }
}
