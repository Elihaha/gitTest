package com.bst.common.entity.pay;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Payment {
    private Byte id;

    private String payName;

    private Byte payType;

    private String payCusNo;

    private String paySecretKey;

    private String payCurrency;

    private String payDetail;

    private String payImage;

    private Boolean payEnable;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public String getPayCusNo() {
        return payCusNo;
    }

    public void setPayCusNo(String payCusNo) {
        this.payCusNo = payCusNo == null ? null : payCusNo.trim();
    }

    public String getPaySecretKey() {
        return paySecretKey;
    }

    public void setPaySecretKey(String paySecretKey) {
        this.paySecretKey = paySecretKey == null ? null : paySecretKey.trim();
    }

    public String getPayCurrency() {
        return payCurrency;
    }

    public void setPayCurrency(String payCurrency) {
        this.payCurrency = payCurrency == null ? null : payCurrency.trim();
    }

    public String getPayDetail() {
        return payDetail;
    }

    public void setPayDetail(String payDetail) {
        this.payDetail = payDetail == null ? null : payDetail.trim();
    }

    public String getPayImage() {
        return payImage;
    }

    public void setPayImage(String payImage) {
        this.payImage = payImage == null ? null : payImage.trim();
    }

    public Boolean getPayEnable() {
        return payEnable;
    }

    public void setPayEnable(Boolean payEnable) {
        this.payEnable = payEnable;
    }
}