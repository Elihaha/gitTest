package com.bst.common.modle.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zouqiang
 * @create 2018-09-25 21:42
 **/
public class DetailResult {
    private String orderStatus;
    private String orderNo;
    private LocalDateTime createTime;
    private LocalDateTime payTime;
    //缺一个配送方式
    private LocalDateTime deliveryTime;
    private String consumerName;
    private String telephone;
    private BigDecimal totalAmount;
    private BigDecimal postage;
    private byte goodsCount;
    private byte payType;
    private LocalDateTime payCreate;
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public byte getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(byte goodsCount) {
        this.goodsCount = goodsCount;
    }

    public byte getPayType() {
        return payType;
    }

    public void setPayType(byte payType) {
        this.payType = payType;
    }

    public LocalDateTime getPayCreate() {
        return payCreate;
    }

    public void setPayCreate(LocalDateTime payCreate) {
        this.payCreate = payCreate;
    }






}
