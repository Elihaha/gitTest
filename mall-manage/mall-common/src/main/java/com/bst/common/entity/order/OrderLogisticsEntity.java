package com.bst.common.entity.order;

import com.alibaba.fastjson.JSON;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 订单物流表
 *
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-17 19:27:22
 */
@Builder
public class OrderLogisticsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String id;
    //
    private Long orderId;
    //
    private String goodsName;
    //
    private Integer goodsCount;
    //
    private String signer;
    //
    private String signerPhone;
    //
    private String province;
    //
    private String city;
    //
    private String area;
    //
    private String address;
    //
        private String postcode;
    //
    private String dhl;
    //
    private String trackingNumber;
    //
    private LocalDateTime inputTime;
    //
    private Long inputUser;
    //
    private LocalDateTime lastUpdate;
    //
    private Long lastOperator;


    /**
     * 设置：
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public String getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置：
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置：
     */
    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    /**
     * 获取：
     */
    public Integer getGoodsCount() {
        return goodsCount;
    }

    /**
     * 设置：
     */
    public void setSigner(String signer) {
        this.signer = signer;
    }

    /**
     * 获取：
     */
    public String getSigner() {
        return signer;
    }

    /**
     * 设置：
     */
    public void setSignerPhone(String signerPhone) {
        this.signerPhone = signerPhone;
    }

    /**
     * 获取：
     */
    public String getSignerPhone() {
        return signerPhone;
    }

    /**
     * 设置：
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置：
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置：
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取：
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置：
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * 获取：
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 设置：
     */
    public void setDhl(String dhl) {
        this.dhl = dhl;
    }

    /**
     * 获取：
     */
    public String getDhl() {
        return dhl;
    }

    /**
     * 设置：
     */
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    /**
     * 获取：
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * 设置：
     */
    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }

    /**
     * 获取：
     */
    public LocalDateTime getInputTime() {
        return inputTime;
    }

    /**
     * 设置：
     */
    public void setInputUser(Long inputUser) {
        this.inputUser = inputUser;
    }

    /**
     * 获取：
     */
    public Long getInputUser() {
        return inputUser;
    }

    /**
     * 设置：
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * 获取：
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 设置：
     */
    public void setLastOperator(Long lastOperator) {
        this.lastOperator = lastOperator;
    }

    /**
     * 获取：
     */
    public Long getLastOperator() {
        return lastOperator;
    }

    public OrderLogisticsEntity() {
    }

    ;



    public OrderLogisticsEntity(String id, Long orderId, String goodsName, Integer goodsCount, String signer, String signerPhone, String province, String city, String area, String address, String postcode, String dhl, String trackingNumber, LocalDateTime inputTime, Long inputUser, LocalDateTime lastUpdate, Long lastOperator) {
        this.id = id;
        this.orderId = orderId;
        this.goodsName = goodsName;
        this.goodsCount = goodsCount;
        this.signer = signer;
        this.signerPhone = signerPhone;
        this.province = province;
        this.city = city;
        this.area = area;
        this.address = address;
        this.postcode = postcode;
        this.dhl = dhl;
        this.trackingNumber = trackingNumber;
        this.inputTime = inputTime;
        this.inputUser = inputUser;
        this.lastUpdate = lastUpdate;
        this.lastOperator = lastOperator;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
