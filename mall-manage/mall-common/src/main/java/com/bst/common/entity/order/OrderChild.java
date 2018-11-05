package com.bst.common.entity.order;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public class OrderChild {
    private Long id;

    private Long mainId;

    private String orderNo;

    private String explain;

    private Integer goodsCount;

    private BigDecimal price;

    private Byte status;

    private Boolean isReturn;

    private Date salesReturnTime;

    private String remark;

    public OrderChild() {
    }

    public OrderChild(Long id, Long mainId, String orderNo, String explain, Integer goodsCount, BigDecimal price, Byte status, Boolean isReturn, Date salesReturnTime, String remark) {
        this.id = id;
        this.mainId = mainId;
        this.orderNo = orderNo;
        this.explain = explain;
        this.goodsCount = goodsCount;
        this.price = price;
        this.status = status;
        this.isReturn = isReturn;
        this.salesReturnTime = salesReturnTime;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}