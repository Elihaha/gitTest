package com.bst.common.modle.order;

import com.alibaba.fastjson.JSON;
import com.bst.common.config.Snowflake.IdGenerate;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.entity.order.OrderLogisticsEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@ApiModel
public class OrderLogisticsInsertPojo {
    @ApiModelProperty(name = "orderId", value = "订单id", example = "4", dataType = "long",required = true)
    private Long orderId;
    //
    @ApiModelProperty(name = "goodsName", value = "商品名字", example = "test", dataType = "string",required = true)
    private String goodsName;
    //
    @ApiModelProperty(name = "goodsCount", value = "商品数量", example = "2", dataType = "int",required = true)
    private Integer goodsCount;
    //
    @ApiModelProperty(name = "signer", value = "收货人", example = "pz", dataType = "string",required = true)
    private String signer;
    //
    @ApiModelProperty(name = "signerPhone", value = "收货人电话", example = "17608413342", dataType = "string",required = true)
    private String signerPhone;
    //
    @ApiModelProperty(name = "province", value = "省份", example = "湖南", dataType = "string",required = true)
    private String province;
    //
    @ApiModelProperty(name = "city", value = "城市", example = "长沙市", dataType = "string",required = true)
    private String city;
    //
    @ApiModelProperty(name = "area", value = "地区", example = "芙蓉区", dataType = "string",required = true)
    private String area;
    //
    @ApiModelProperty(name = "address", value = "详细地址", example = "不知名的地方1", dataType = "string",required = true)
    private String address;
    //
    @ApiModelProperty(name = "postcode", value = "邮编", example = "41000", dataType = "string",required = true)
    private String postcode;
    //
    @ApiModelProperty(name = "dhl", value = "快递公司", example = "yuantong", dataType = "string",required = true)
    private String dhl;
    //
    @ApiModelProperty(name = "trackingNumber", value = "相关物流信息编号", example = "889999834560284183", dataType = "string",required = true)
    private String trackingNumber;

    //
    @ApiModelProperty(name = "status", value = "状态", example = "1", dataType = "int",required = true)
    private  Integer status;

    public OrderLogisticsInsertPojo() {
    }

    public OrderLogisticsInsertPojo(Long orderId, String goodsName, Integer goodsCount, String signer, String signerPhone, String province, String city, String area, String address, String postcode, String dhl, String trackingNumber, Integer status) {
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
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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



    public OrderLogisticsEntity castOrderLogisticsEntity(Integer id){
       return  OrderLogisticsEntity.builder()
               .id(IdGenerate.generate(IdGenerate.LOCALDATETIME_NO_PREFIX))
               .address(this.address)
               .area(this.area)
               .city(this.city)
               .dhl(this.dhl)
               .goodsCount(this.goodsCount)
               .goodsName(this.goodsName)
               .province(this.postcode)
               .postcode(this.postcode)
               .orderId(this.orderId)
               .signer(this.signer)
               .signerPhone(this.signerPhone)
               .trackingNumber(this.trackingNumber)

               .inputTime(LocalDateTime.now())
               .lastUpdate(LocalDateTime.now())
               .inputUser(id.longValue())
               .lastOperator(id.longValue())
               .build();
    }

    public OrderLogisticsVO castOrderLogisticsPojo(Integer userId){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  OrderLogisticsVO.builder()
                .address(this.address)
                .area(this.area)
                .city(this.city)
                .dhl(this.dhl)
                .goodsCount(this.goodsCount)
                .goodsName(this.goodsName)
                .province(this.postcode)
                .postcode(this.postcode)
                .orderNo(this.orderId+"")
                .signer(this.signer)
                .signerPhone(this.signerPhone)
                .trackingNumber(this.trackingNumber)

                .inputUser(userId.longValue())
                .status(this.status)
                .build();

    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
