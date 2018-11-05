package com.bst.common.modle.order;

import com.bst.common.entity.order.OrderDetail;

public class OrderDetailMonitoringInfo extends OrderDetail {


    private  Integer goodsCount;

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
}
