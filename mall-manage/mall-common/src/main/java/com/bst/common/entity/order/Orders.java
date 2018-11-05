package com.bst.common.entity.order;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;
public class Orders {
    public Orders(Long id, Byte orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }

    public Orders() {
    }

    private Long id;

    private String orderNo;

    private String consumerId;

    private String consumerName;

    private String telephone;

    private Long shopId;

    private String shopName;

    private Date createTime;

    private Byte orderStatus;

    private Byte payStatus;

    private Integer goodsCount;

    private BigDecimal totalAmount;

    private String consumerRemark;

    private Byte payType;

    private Date payTime;

    private Boolean isReturn;

    private Date salesReturnTime;

    private Boolean haveChild;

    private Date lastUpdate;

    private Date deliveryTime;

    private BigDecimal postage;
    private int timestampNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId == null ? null : consumerId.trim();
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName == null ? null : consumerName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getConsumerRemark() {
        return consumerRemark;
    }

    public void setConsumerRemark(String consumerRemark) {
        this.consumerRemark = consumerRemark == null ? null : consumerRemark.trim();
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Boolean getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Boolean isReturn) {
        this.isReturn = isReturn;
    }

    public Date getSalesReturnTime() {
        return salesReturnTime;
    }

    public void setSalesReturnTime(Date salesReturnTime) {
        this.salesReturnTime = salesReturnTime;
    }

    public Boolean getHaveChild() {
        return haveChild;
    }

    public void setHaveChild(Boolean haveChild) {
        this.haveChild = haveChild;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public int getTimestampNum() {
        return timestampNum;
    }
    public double getDoubleTimestampNum() {
        return timestampNum;
    }
    public void setTimestampNum(int timestampNum) {
        this.timestampNum = timestampNum;
    }
}