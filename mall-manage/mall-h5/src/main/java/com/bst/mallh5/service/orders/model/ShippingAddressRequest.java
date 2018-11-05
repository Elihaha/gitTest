package com.bst.mallh5.service.orders.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zouqiang
 * @create 2018-09-28 14:28
 **/
public class ShippingAddressRequest {
    @ApiModelProperty("收货人名字")
    private String signer;
    @ApiModelProperty("收货人电话")
    private String signerPhone;
    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("市")
    private String city;
    @ApiModelProperty("区")
    private String area;
    @ApiModelProperty("详细地址")
    private String address;
    @ApiModelProperty("邮编")
    private String postcode;
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







}
