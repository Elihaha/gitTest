package com.bst.order.service;

import com.bst.common.entity.order.OrderPostageEntity;

import java.util.List;
import java.util.Map;

/**
 * 运费配置
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
public interface OrderPostageService {
	
	OrderPostageEntity queryObject(Long id);
	
	List<OrderPostageEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderPostageEntity orderPostage);
	
	void update(OrderPostageEntity orderPostage);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
