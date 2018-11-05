package com.bst.order.service.impl;

import com.bst.common.entity.order.OrderPostageEntity;
import com.bst.common.mapper.order.OrderPostageDao;
import com.bst.order.service.OrderPostageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("orderPostageService")
@Transactional(value = "mallTransactionManager" ,readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class OrderPostageServiceImpl implements OrderPostageService {
	@Autowired
	private OrderPostageDao orderPostageDao;
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public OrderPostageEntity queryObject(Long id){
		return orderPostageDao.queryObject(id);
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<OrderPostageEntity> queryList(Map<String, Object> map){
		return orderPostageDao.queryList(map);
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int queryTotal(Map<String, Object> map){
		return orderPostageDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderPostageEntity orderPostage){
		orderPostageDao.save(orderPostage);
	}
	
	@Override
	public void update(OrderPostageEntity orderPostage){
		orderPostageDao.update(orderPostage);
	}
	
	@Override
	public void delete(Long id){
		orderPostageDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		orderPostageDao.deleteBatch(ids);
	}
	
}
