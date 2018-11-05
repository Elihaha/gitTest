package com.bst.mallh5.service.order;

import com.bst.common.entity.order.OrderLogisticsEntity;
import com.bst.common.modle.Result;

import java.util.List;
import java.util.Map;

/**
 * 订单物流表
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-17 19:27:22
 */
public interface OrderLogisticsHService {
	
	Result queryObject(String id);
	
	List<OrderLogisticsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderLogisticsEntity orderLogistics);
	
	void update(OrderLogisticsEntity orderLogistics);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
