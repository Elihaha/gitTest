package com.bst.common.mapper.order;

import com.bst.common.entity.order.OrderDetail;
import com.bst.common.entity.order.OrderDetailExample;
import java.util.List;

import com.bst.common.modle.order.OrderDetailMonitoringInfo;
import com.bst.common.modle.orders.DetailResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface OrderDetailMapper {
    int countByExample(OrderDetailExample example);

    int deleteByExample(OrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExample(OrderDetailExample example);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    List<DetailResult> queryOrderDetail(Long orderId);

    OrderDetail selectOrderDetailByOrderId(Long orderId);

    OrderDetailMonitoringInfo queryOrderDetailByOrderNo(@Param("ordersNo") String ordersNo);
}