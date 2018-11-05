package com.bst.order.service.impl;


import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.entity.order.OrderChild;
import com.bst.common.entity.order.OrderChildExample;
import com.bst.common.entity.order.OrderLogisticsEntity;
import com.bst.common.mapper.order.OrderChildMapper;
import com.bst.common.mapper.order.OrderLogisticsDao;
import com.bst.common.modle.order.OrderLogisticsDto;
import com.bst.common.modle.order.OrderLogisticsInsertPojo;
import com.bst.common.modle.order.OrderLogisticsVO;
import com.bst.common.modle.order.OrderLogisticsUpdateDto;
import com.bst.common.pojo.Pagination;
import com.bst.common.utils.RedisParam;
import com.bst.order.service.CourierCompanyService;
import com.bst.order.service.OrderLogisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service("orderLogisticsService")
@Transactional(readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class OrderLogisticsServiceImpl implements OrderLogisticsService {
	@Autowired
	private OrderLogisticsDao orderLogisticsDao;
	@Autowired
	OrderChildMapper orderChildMapper;
	@Autowired
	OrderLogisticsService orderLogisticsService;

	@Autowired
	JedisCluster jedisCluster;
	@Autowired
	CourierCompanyService courierCompanyService;

	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public OrderLogisticsEntity queryObject(Long id){
		return orderLogisticsDao.queryObject(id);
	}

	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<OrderLogisticsVO> queryList(Pagination pagination, Map<String, Object> like){
		HashMap<String, Object> stringObjectHashMap = getStringObjectHashMap( pagination);
		stringObjectHashMap.putAll(like);
        List<OrderLogisticsDto> orderLogisticsDtos = orderLogisticsDao.queryOrderLogisticsDtoList(stringObjectHashMap);
        List<OrderLogisticsVO> collect = orderLogisticsDtos.stream()
				.map(orderLogisticsDto ->{
							OrderLogisticsVO orderLogisticsVO = orderLogisticsDto.castOrderLogisticsPojo(PermissionInfoUtil.getCurrentLogginUser().getId());
							orderLogisticsVO.setDhl(jedisCluster.get(RedisParam.COURIER_COMPANY+orderLogisticsVO.getDhl()));
				            return  orderLogisticsVO;
				  }
						)
				.collect(Collectors.toList());
        return collect;
	}

	private HashMap<String, Object> getStringObjectHashMap(Pagination pagination) {
		return new HashMap<String, Object>() {{
			Integer page = pagination.getPage();
			Integer number = pagination.getNumber();
			int value = (page - 1) * number;
			put("offset", value<0?0:value);
			put("limit", number);
		}};
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int queryTotal(Pagination pagination, Map<String, Object> like){
		HashMap<String, Object> stringObjectHashMap = getStringObjectHashMap( pagination);
		stringObjectHashMap.putAll(like);
		return orderLogisticsDao.queryTotal(stringObjectHashMap);
	}
	
	@Override
	public void save(OrderLogisticsInsertPojo orderLogistics){
		Operator currentLogginUser = PermissionInfoUtil.getCurrentLogginUser();
		Integer id = currentLogginUser.getId();
//		Integer id = 2;
		orderLogisticsDao.save(orderLogistics.castOrderLogisticsEntity(id));
	}
	
	@Override
	public void update(OrderLogisticsUpdateDto orderLogistics){

		OrderLogisticsEntity orderLogisticsEntity = orderLogisticsDao.getOrderLogisticsEntitiesByOrderNO(orderLogistics.getOrderNo());
		Operator currentLogginUser = PermissionInfoUtil.getCurrentLogginUser();
		Integer id = currentLogginUser.getId();
//		Integer id = 1;
		orderLogisticsEntity.setInputTime(null);
		orderLogisticsEntity.setInputUser(null);
		orderLogisticsEntity.setLastUpdate(LocalDateTime.now());
		orderLogisticsEntity.setLastOperator(id.longValue());

		orderLogisticsEntity.setDhl(orderLogistics.getDhl());
		orderLogisticsEntity.setTrackingNumber(orderLogistics.getTrackingNumber());
		orderLogisticsDao.update(orderLogisticsEntity);
	}
	
	@Override
	public void delete(Long id){
		orderLogisticsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		orderLogisticsDao.deleteBatch(ids);
	}

	@Override
	public void updateByPrimaryKey(Optional<List<OrderLogisticsDto>> orderLogisticsDtos) {
		orderLogisticsDtos.ifPresent(orderLogisticsDtos1 -> {
			orderLogisticsDtos1.stream().forEach(orderLogisticsDto -> {
				log.info("{}",orderLogisticsDto.toString());
//				orderChildMapper.updateByOrderNo(orderLogisticsDto.getOrderId(),new Byte("6"));
				orderLogisticsService.update(OrderLogisticsUpdateDto.builder().dhl(orderLogisticsDto.getDhl()).trackingNumber(orderLogisticsDto.getTrackingNumber()).orderNo(orderLogisticsDto.getOrderId()).build());
			});

		});
	}

	@Override
	public void updateByOrderNo(OrderLogisticsUpdateDto orderLogistics) {
		OrderChildExample goodsSkuExample = new OrderChildExample();
		goodsSkuExample.createCriteria().andOrderNoEqualTo(orderLogistics.getOrderNo());
		orderChildMapper.updateByOrderNo(orderLogistics.getOrderNo() ,new  Byte("6"));

	}

	@Override
	public OrderLogisticsEntity getOrderLogisticsEntitiesByOrderId(Long orderId) {
		return orderLogisticsDao.getOrderLogisticsEntitiesByOrderId(orderId);
	}

	@Override
	public OrderLogisticsEntity getOrderLogisticsEntitiesByOrderNO(String orderNo) {
		return orderLogisticsDao.getOrderLogisticsEntitiesByOrderNO(orderNo);
	}

}
