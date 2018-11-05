package com.bst.common.entity.goods;

import java.util.Date;

public class GoodsSpec {
    private Long id;

    private String specNo;

    private String specName;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecNo() {
        return specNo;
    }

    public void setSpecNo(String specNo) {
        this.specNo = specNo == null ? null : specNo.trim();
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
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