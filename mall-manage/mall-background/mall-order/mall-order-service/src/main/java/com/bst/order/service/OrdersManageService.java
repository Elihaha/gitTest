package com.bst.order.service;


import com.bst.common.constants.OrdersConstants;
import com.bst.common.constants.ReturnOfGoodsConstants;
import com.bst.common.entity.order.OrderChild;
import com.bst.common.entity.order.OrderLogisticsEntity;
import com.bst.common.entity.order.Orders;
import com.bst.common.modle.Result;
import com.bst.common.modle.order.OrderChildGenerate;
import com.bst.order.model.OrdersPageQuery;

import java.util.List;
import java.util.Optional;

/**
 * 订单管理
 *
 * @author zouqiang
 * @create 2018-09-19 9:51
 **/
public interface OrdersManageService {
    //主订单
    public Orders queryOrders(Long OrdersId);

    //主订单
    public Orders queryOrdersByOrderNo(String OrdersId);

    //查询所有主订单
    public List<Orders> queryOrdersList(Integer page, Integer rows);

    //查询所有子订单
    public List<OrderChild> queryOrderChildsList(Long OrdersId);

    //查询子订单
    public OrderChild queryOrderChild(Long OrderChildId);

    //修改主订单
    // public int updateOrders(Orders orders);

    //修改订单状态
    public int updateOrderStatus(Long ordersId, int orderStatus);
    //修改订单状态
    public int updateOrderStatus(String orderNo, OrdersConstants orderStatus, OrdersConstants payStatus);


    //查询input_time
    public OrderLogisticsEntity queryByOrderChild(Long OrderChildId);

    ////创建子订单
    public Optional<OrderChild> createOrderChild(OrderChildGenerate orderChild);

    public OrderChild createOrderChild(Long orderId, String explain, int goodsCount, String remark);

    //插入子订单
    public void insertOrderChild(OrderChild orderChild);

    public Result queryOrdersList(OrdersPageQuery query);

    public Result queryOrdersListBy(OrdersPageQuery query);

    //根据orderNo查询Orders
    public Orders selectByOrderNo(String orderNo);

    public int updateByPrimaryKeySelective(Orders orders);

    public Orders selectByPrimaryKey(long id);

    void ReturnGoods(Orders orders);

    long orderTimeout(String orderNo);

    int updateOrderStatusTimeOut();

    List<Orders> initOrderToRedis(Long id);

    int updateOrderReturnStatus(String orderNo,OrdersConstants ordersConstants);

    void updateReturnStatus(String orderNo, ReturnOfGoodsConstants systemRefund);
}