package com.bst.mallh5.service.orders.model;

/**
 * @author zouqiang
 * @create 2018-09-28 22:13
 **/
public class OrdersRequest {

    private long goodsSkuId ;
    private  int count;

    public long getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(long goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
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

    private ShippingAddressRequest shippingAddressRequest;
}
