package com.bst.common.entity.shop;

public class ShippingAddress {
    private Long id;

    private Long orderdId;

    private String signer;

    private String signerPhone;

    private String province;

    private String city;

    private String area;

    private String address;

    private String postcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderdId() {
        return orderdId;
    }

    public void setOrderdId(Long orderdId) {
        this.orderdId = orderdId;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer == null ? null : signer.trim();
    }

    public String getSignerPhone() {
        return signerPhone;
    }

    public void setSignerPhone(String signerPhone) {
        this.signerPhone = signerPhone == null ? null : signerPhone.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }
}