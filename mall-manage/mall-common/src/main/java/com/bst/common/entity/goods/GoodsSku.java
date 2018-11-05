package com.bst.common.entity.goods;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder(toBuilder = true)
public class GoodsSku {
    private Long id;

    private String skuNo;

    private String skuName;

    private BigDecimal costPrice;

    private BigDecimal marketPrice;

    private BigDecimal pricing;

    private BigDecimal sellPrice;

    private Integer stock;

    private Long spuId;

    private Integer showWeight;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Byte skuStatus;

    private Boolean isSell;

    private Integer soldoutCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo == null ? null : skuNo.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getPricing() {
        return pricing;
    }

    public void setPricing(BigDecimal pricing) {
        this.pricing = pricing;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Integer getShowWeight() {
        return showWeight;
    }

    public void setShowWeight(Integer showWeight) {
        this.showWeight = showWeight;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Byte getSkuStatus() {
        return skuStatus;
    }

    public void setSkuStatus(Byte skuStatus) {
        this.skuStatus = skuStatus;
    }

    public Boolean getIsSell() {
        return isSell;
    }

    public void setIsSell(Boolean isSell) {
        this.isSell = isSell;
    }

    public Integer getSoldoutCount() {
        return soldoutCount;
    }

    public void setSoldoutCount(Integer soldoutCount) {
        this.soldoutCount = soldoutCount;
    }

    public GoodsSku() {
    }

    public GoodsSku(Long id, String skuNo, String skuName, BigDecimal costPrice, BigDecimal marketPrice, BigDecimal pricing, BigDecimal sellPrice, Integer stock, Long spuId, Integer showWeight, Date gmtCreate, Date gmtUpdate, Byte skuStatus, Boolean isSell, Integer soldoutCount) {
        this.id = id;
        this.skuNo = skuNo;
        this.skuName = skuName;
        this.costPrice = costPrice;
        this.marketPrice = marketPrice;
        this.pricing = pricing;
        this.sellPrice = sellPrice;
        this.stock = stock;
        this.spuId = spuId;
        this.showWeight = showWeight;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.skuStatus = skuStatus;
        this.isSell = isSell;
        this.soldoutCount = soldoutCount;
    }
}