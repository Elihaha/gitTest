package com.bst.order.service;

import com.bst.common.entity.order.OrderLogisticsEntity;
import com.bst.common.modle.order.OrderLogisticsDto;
import com.bst.common.modle.order.OrderLogisticsInsertPojo;
import com.bst.common.modle.order.OrderLogisticsVO;
import com.bst.common.modle.order.OrderLogisticsUpdateDto;
import com.bst.common.pojo.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 运费
 * 订单物流表
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-17 19:27:22
 */
public interface OrderLogisticsService {
	
	OrderLogisticsEntity queryObject(Long id);
	
	List<OrderLogisticsVO> queryList(Pagination pagination, Map<String, Object> like);
	
	int queryTotal(Pagination pagination, Map<String, Object> like);
	
	void save(OrderLogisticsInsertPojo orderLogistics);
	
	void update(OrderLogisticsUpdateDto orderLogistics);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	void updateByPrimaryKey(Optional<List<OrderLogisticsDto>> orderLogisticsDtos);

	void updateByOrderNo(OrderLogisticsUpdateDto orderLogisticsUpdateDto);

	OrderLogisticsEntity getOrderLogisticsEntitiesByOrderId(Long orderId);
	OrderLogisticsEntity getOrderLogisticsEntitiesByOrderNO(String orderNo);
}
