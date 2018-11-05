package com.bst.mallh5.service.pay;


import com.bst.common.entity.pay.Payment;

import java.util.List;

/**
 * 付款方式接口定义
 */
public interface IPaymentService{
	
	/**查询用户所拥有的付款方式，包括通用的和拥有的高级付款方式
	 * @return
	 */
	List<Payment> queryPayments();
	
}
