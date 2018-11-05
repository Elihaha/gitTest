package com.bst.common.modle.order;

import com.alibaba.fastjson.JSON;
import com.bst.common.config.ExpressType;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.entity.order.OrderLogisticsEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.lang.annotation.ElementType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@ApiModel
public class OrderLogisticsDto {

    private String id;
    //
    private String orderNo;
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
    @ApiModelProperty(hidden = true)
    private LocalDateTime inputTime;
    //
    private String inputUser;
    //
    @ApiModelProperty(hidden = true)
    private LocalDateTime lastUpdate;
    //
    private String lastOperator;

    //
    private  Integer status;

    public OrderLogisticsDto() {
    }

    public OrderLogisticsDto(String id, String orderId, String goodsName, Integer goodsCount, String signer, String signerPhone, String province, String city, String area, String address, String postcode, String dhl, String trackingNumber, LocalDateTime inputTime, String inputUser, LocalDateTime lastUpdate, String lastOperator, Integer status) {
        this.id = id;
        this.orderNo = orderNo;
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
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderNo;
    }

    public void setOrderId(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getSignerPhone() {
        return signerPhone;
    }

    public void setSignerPhone(String signerPhone) {
        this.signerPhone = signerPhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getDhl() {
        return dhl;
    }

    public void setDhl(String dhl) {
        this.dhl = dhl;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public LocalDateTime getInputTime() {
        return inputTime;
    }

    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }

    public String getInputUser() {
        return inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    public OrderLogisticsEntity castOrderLogisticsEntity(Integer id){
       return  OrderLogisticsEntity.builder()
               .address(this.address)
               .area(this.area)
               .city(this.city)
               .dhl(this.dhl)
               .goodsCount(this.goodsCount)
               .goodsName(this.goodsName)
               .province(this.province)
               .postcode(this.postcode)
               .id(this.id!=null?this.id: SnowflakeId.getId()+"")
               .orderId(this.orderNo!=null? Long.valueOf(this.orderNo) :SnowflakeId.getId())
               .signer(this.signer)
               .signerPhone(this.signerPhone)
               .trackingNumber(this.trackingNumber)

               .inputTime(LocalDateTime.now())
               .lastUpdate(LocalDateTime.now())
               .inputUser(id.longValue())
               .lastOperator(id.longValue())
               .build();
    }

    public OrderLogisticsVO castOrderLogisticsPojo(Integer id){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  OrderLogisticsVO.builder()
                .address(this.address)
                .area(this.area)
                .city(this.city)
                .dhl(ExpressType.getNameByCode(this.id))
                .goodsCount(this.goodsCount)
                .goodsName(this.goodsName)
                .province(this.province)
                .postcode(this.postcode)
                .id(this.id!=null?this.id: SnowflakeId.getId()+"")
                .orderNo(this.orderNo)
                .signer(this.signer)
                .signerPhone(this.signerPhone)
                .trackingNumber(this.trackingNumber)
                .dhl(this.dhl)
                .inputTime(df.format(this.inputTime))
                .lastUpdate(df.format(this.lastUpdate))
//                .inputUser(id.longValue())
                .lastOperator(this.lastOperator)
                .status(this.status)
                .build();

    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
