package com.bst.mallh5.service.order.impl;

import com.bst.common.entity.order.OrderLogisticsEntity;
import com.bst.common.constants.HttpConstants;
import com.bst.common.mapper.order.OrderLogisticsDao;
import com.bst.common.modle.Result;
import com.bst.common.pojo.QueryKuaidiJson;
import com.bst.common.utils.KuaiDI100Util;
import com.bst.mallh5.service.order.OrderLogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service("orderLogisticsService")
@Transactional(readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class OrderLogisticsServiceImpl implements OrderLogisticsService {
	@Autowired
	private OrderLogisticsDao orderLogisticsDao;
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Result queryObject(Long id){
		//   根据子订单id  获取  物流编号
		OrderLogisticsEntity orderLogisticsEntity = orderLogisticsDao.getOrderLogisticsEntitiesByOrderId(id);
		String trackingNumber = orderLogisticsEntity.getTrackingNumber();
//            String trackingNumber = null;
//            trackingNumber = 889999834560284183L+"";

		// 根据  物流编号 获取相关信息
		Optional<QueryKuaidiJson> queryKuaidiJson = KuaiDI100Util.kuaiDiQuery(trackingNumber);
		if (!queryKuaidiJson.isPresent()) {
			return Result.ok(HttpConstants.SUCCESS,"没有任何数据");
		}
		return Result.ok(queryKuaidiJson.get());
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<OrderLogisticsEntity> queryList(Map<String, Object> map){
		return orderLogisticsDao.queryList(map);
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int queryTotal(Map<String, Object> map){
		return orderLogisticsDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderLogisticsEntity orderLogistics){
		orderLogisticsDao.save(orderLogistics);
	}
	
	@Override
	public void update(OrderLogisticsEntity orderLogistics){
		orderLogisticsDao.update(orderLogistics);
	}
	
	@Override
	public void delete(Long id){
		orderLogisticsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		orderLogisticsDao.deleteBatch(ids);
	}
	
}
