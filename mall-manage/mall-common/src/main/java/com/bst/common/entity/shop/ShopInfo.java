package com.bst.common.entity.shop;

import java.io.Serializable;
import java.util.Date;

public class ShopInfo implements Serializable {

    private static final long serialVersionUID = -7962465868545202909L;

    private Long id;

    private Integer permissionId;

    private String shopName;

    private String account;

    private Byte shopStatus;

    private Date gmtCreate;

    private Date openTime;

    private Date gmtUpdate;

    private String operator;

    public ShopInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Byte getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Byte shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}