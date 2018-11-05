package com.bst.common.entity.goods;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsSafeguard {
    private Long id;

    private String safeguardName;

    private BigDecimal price;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSafeguardName() {
        return safeguardName;
    }

    public void setSafeguardName(String safeguardName) {
        this.safeguardName = safeguardName == null ? null : safeguardName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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