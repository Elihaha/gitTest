package com.bst.mallh5.service.orders;
import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.entity.order.OrderChild;
import com.bst.common.entity.order.OrderDetail;
import com.bst.common.entity.order.Orders;
import com.bst.common.entity.pay.Payment;
import com.bst.common.entity.shop.ShippingAddress;
import com.bst.common.modle.Result;
import com.bst.common.modle.orders.DetailResult;
import com.bst.mallh5.model.orders.OrdersCreatePOJO;
import com.bst.mallh5.service.orders.model.ShippingAddressRequest;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单业务层
 *
 * @author zouqiang
 * @create 2018-09-17 17:27
 **/
public interface OrderService {

    public void beforeInsertOrder( OrdersCreatePOJO build);

//    public Result createOrder(  long goodsSpuId, int count, String consumerRemark);

    //创建订单详细
    public int createOrderDetail(Long orderId,String orderNo,String spuNo,String skuNo,String orderDetails);


    //查询订单详情
    public List queryOrderDetail(Long orderid);

    /**
     * 获取订单详情表中数据
     * @param orderId 订单id
     * @return 订单详情
     */
    OrderDetail getOrderDetailByOrderId(Long orderId);

    //查询所有主订单
    public Map queryOrdersList(Integer page, Integer rows,Byte orderStatus);


    //通过订单号查询订单
    public Orders queryOrdersByOrdersNo(String OrderNo);

    //更行更新spu的库存
    public boolean updateSpuStock(String spuid ,int count);
    //更行更新sku的库存
    public boolean updateSkuStock(String skuNo ,int count);

    //更新支付时间，和支付方式,订单状态，支付状态
    public  int updatePayType(long orderid,Date paytime,int paytype,byte orderstatus,byte paystatus);

    //更新订单状态
    public int updateOrderStatus(long orderid,Byte orderstatus);

    //更新订单收货状态
    public int updateOrderStatus(long orderid);

    void updateByPrimaryKeySelective(Orders order);

    long updateOrderFinishPay(String orderNo, byte payType, byte fromStatus);

    // 查询订单详情状态
    Map  queryOrderDetailStatus();


}
