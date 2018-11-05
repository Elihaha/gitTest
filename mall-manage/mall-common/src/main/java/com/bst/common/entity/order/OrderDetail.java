package com.bst.common.entity.order;

public class OrderDetail {
    private int id;

    private Long orderId;

    private String orderNo;

    private String spuNo;

    private String skuNo;

    private String orderDetails;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getSpuNo() {
        return spuNo;
    }

    public void setSpuNo(String spuNo) {
        this.spuNo = spuNo;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails == null ? null : orderDetails.trim();
    }

}