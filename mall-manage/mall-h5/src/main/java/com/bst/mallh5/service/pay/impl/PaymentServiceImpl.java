package com.bst.mallh5.service.pay.impl;

import com.bst.common.entity.pay.Payment;
import com.bst.common.mapper.pay.OrderPayWayMapper;
import com.bst.mallh5.service.pay.IPaymentService;
import com.bst.mallh5.user.PlatformUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 付款方式接口实现类
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

	@Resource
	private OrderPayWayMapper orderPayWayMapper;


	@Override
	public List<Payment> queryPayments() {
		return orderPayWayMapper.selectAll();
	}
}
