package com.bst.order.service.impl;

import com.bst.common.entity.order.ProvinceCityEntity;
import com.bst.common.mapper.order.ProvinceCityDao;
import com.bst.order.service.ProvinceCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("provinceCityService")
@Transactional(value = "mallTransactionManager" ,readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class ProvinceCityServiceImpl implements ProvinceCityService {
	@Autowired
	private ProvinceCityDao provinceCityDao;
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public ProvinceCityEntity queryObject(Integer id){
		return provinceCityDao.queryObject(id);
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<ProvinceCityEntity> queryList(Map<String, Object> map){
		return provinceCityDao.queryList(map);
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int queryTotal(Map<String, Object> map){
		return provinceCityDao.queryTotal(map);
	}
	
	@Override
	public void save(ProvinceCityEntity provinceCity){
		provinceCityDao.save(provinceCity);
	}
	
	@Override
	public void update(ProvinceCityEntity provinceCity){
		provinceCityDao.update(provinceCity);
	}
	
	@Override
	public void delete(Integer id){
		provinceCityDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		provinceCityDao.deleteBatch(ids);
	}
	
}
