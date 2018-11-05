package com.bst.common.mapper.order;

import com.bst.common.entity.order.OrderLogisticsEntity;
import com.bst.common.modle.order.OrderLogisticsDto;
import com.bst.common.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单物流表
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-17 19:27:22
 */
@Mapper
public interface OrderLogisticsDao extends BaseDao<OrderLogisticsEntity> {
    List<OrderLogisticsDto> queryOrderLogisticsDtoList(Map<String, Object> map);

    public  OrderLogisticsEntity getOrderLogisticsEntitiesByOrderId(@Param("orderId") Long orderId);
    public  OrderLogisticsEntity getOrderLogisticsEntitiesByOrderNO(@Param("orderNo") String orderNo);
    public  OrderLogisticsEntity getOrderLogisticsEntitiesByMainNo(@Param("orderNo") String mainNo);

}
