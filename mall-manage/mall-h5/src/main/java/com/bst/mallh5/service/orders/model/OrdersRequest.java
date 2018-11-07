package com.bst.mallh5.service.orders.model;

/**
 * @author zouqiang
 * @create 2018-09-28 22:13
 **/
public class OrdersRequest {

    private String goodsSkuNo ;
    private  int count;
    private ShippingAddressRequest shippingAddressRequest;

    public String getGoodsSkuNo() {
        return goodsSkuNo;
    }

    public void setGoodsSkuNo(String goodsSkuNo) {
        this.goodsSkuNo = goodsSkuNo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ShippingAddressRequest getShippingAddressRequest() {
        return shippingAddressRequest;
    }

    public void setShippingAddressRequest(ShippingAddressRequest shippingAddressRequest) {
        this.shippingAddressRequest = shippingAddressRequest;
    }
}

