package com.bst.common.mapper.order;

import com.bst.common.entity.order.Orders;
import com.bst.common.entity.order.OrdersExample;
import com.bst.common.modle.order.AllOrdersQuery;
import com.bst.common.modle.order.OrderChildDto;
import com.bst.common.modle.orders.DetailResult;
import com.bst.common.modle.orders.OrdersResult;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrdersMapper {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);


    Orders selectByOrderNo(OrdersExample example);

    List<Orders> selectByMap(Map map);

    //  List<OrdersResult>   queryListByRecord(OrdersQuery ordersQuer);
    List<OrdersResult>   queryListByRecord(Map map);

    DetailResult queryOrderDetail(Long orderid);
    List<AllOrdersQuery> queryAllOrderDetail(Map map);

    int insertOrdersStatus(Long orderId);

    OrderChildDto   queryOrderChildDtoByOrderNo(@Param("orderNo") String orderNo);

    int updateByOrderNo(@Param("orderNo") String orderNo, @Param("status") byte status, @Param("lastUpdate") LocalDateTime lastUpdate);

    long updateOrderPayStatus(@Param("orderNo") String orderNo, @Param("payType") Byte payType, @Param("fromStatus") Byte fromStatus);

    long orderTimeout(String orderNo);

    int updateOrderStatusTimeOut();

    List<Orders> initOrderToRedis(Long id);

   int  queryCount(Map map);


     int updateOrderStatus(@Param("orderNo") String orderNo, @Param("orderStatus") byte orderStatus,@Param("payStatus") byte payStatus);

    int updateOrderReturnStatus(@Param("orderNo") String orderNo, @Param("statusNo") Byte statusNo);

    void updateReturnStatus(@Param("orderNo") String orderNo, @Param("statusNo") int ordinal);
}