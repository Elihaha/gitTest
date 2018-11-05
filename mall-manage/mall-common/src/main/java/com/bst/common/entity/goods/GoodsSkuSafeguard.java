package com.bst.common.entity.goods;

import java.util.Date;

public class GoodsSkuSafeguard {
    private Long id;

    private Long skuId;

    private Long safeguardId;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSafeguardId() {
        return safeguardId;
    }

    public void setSafeguardId(Long safeguardId) {
        this.safeguardId = safeguardId;
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
}