package com.bst.common.modle.order;

import com.bst.common.entity.order.Orders;
import com.bst.common.modle.goods.GoodsPicNameQuery;

/**
 * @author zouqiang
 * @create 2018-09-27 0:53
 **/
public class AllOrdersQuery extends Orders {
    //OrderDetail中的订单详情
    private String  orderDetails;
    //由json 字符串orderDetails转成 json对象orderDetailsList
    private GoodsPicNameQuery orderDetailsList;
    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }


    public GoodsPicNameQuery getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(GoodsPicNameQuery orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }
}
