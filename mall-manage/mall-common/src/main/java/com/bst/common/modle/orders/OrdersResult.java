package com.bst.common.modle.orders;

import com.bst.common.entity.order.Orders;

/**
 * @author zouqiang
 * @create 2018-09-25 10:43
 **/
public class OrdersResult extends Orders {
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 商品名字
     */
    //get set
    private String goodsName;

}
