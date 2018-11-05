package com.bst.order.service.impl;

import com.bst.common.entity.order.CourierCompany;
import com.bst.common.entity.order.OrderPostageEntity;
import com.bst.common.mapper.order.CourierCompanyMapper;
import com.bst.common.mapper.order.OrderPostageDao;
import com.bst.order.service.CourierCompanyService;
import com.bst.order.service.OrderPostageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("courierCompanyService")
@Transactional(readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class CourierCompanyServiceImpl implements CourierCompanyService {
	@Autowired
	private CourierCompanyMapper courierCompany;
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public CourierCompany queryObject(Long id){
		return courierCompany.selectByPrimaryKey(id);
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<CourierCompany> queryList(Map<String, Object> map){
		return courierCompany.list(map);
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int queryTotal(Map<String, Object> map){
		return 0;
	}
	
	@Override
	public void save(CourierCompany orderPostage){
		courierCompany.insert(orderPostage);
	}
	
	@Override
	public void update(CourierCompany orderPostage){
		courierCompany.updateByPrimaryKey(orderPostage);
	}
	
	@Override
	public void delete(Long id){
		courierCompany.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		;
	}
	
}
