package com.bst.mallh5.service.orders.impl;

import com.bst.common.mapper.order.OrderRefundMapper;
import com.bst.mallh5.service.orders.OrderRefundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zouqiang
 * @create 2018-11-05 11:32
 **/
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class OrderRefundServiceImpl implements OrderRefundService {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderRefundServiceImpl.class);
    @Autowired
    private OrderRefundMapper orderRefundMapper;

    /**
     * 取消退款
      */
    @Override
    public void cancelRefund(String orderNo){
        try {
            orderRefundMapper.updateRefundByOrderNo(orderNo);
        }catch (Exception ex){
            LOGGER.error("取消退款错误",ex);
        }
    }
}
