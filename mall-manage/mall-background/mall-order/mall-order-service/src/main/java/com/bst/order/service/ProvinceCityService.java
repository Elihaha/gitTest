package com.bst.order.service;

import com.bst.common.entity.order.ProvinceCityEntity;

import java.util.List;
import java.util.Map;

/**
 * 城市配置表
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-17 19:27:22
 */
public interface ProvinceCityService {
	
	ProvinceCityEntity queryObject(Integer id);
	
	List<ProvinceCityEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ProvinceCityEntity provinceCity);
	
	void update(ProvinceCityEntity provinceCity);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
