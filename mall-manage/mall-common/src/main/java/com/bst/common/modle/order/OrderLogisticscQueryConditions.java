package com.bst.common.modle.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/25 9:55 2018 09
 */
@ApiModel
public class OrderLogisticscQueryConditions {

    @ApiModelProperty(name = "status", value = "狀態 、6发货中、7已收货  20  全部" ,example = "7",required = true)
    private  Integer  status;
    @ApiModelProperty(name = "orderId", value = "订单id" ,example = "")
    private  Long  orderId;
    @ApiModelProperty(name = "signer", value = "收货人" ,example = "")
    private  String signer;
    @ApiModelProperty(name = "signerPhone", value = "收货人电话" ,example = "")
    private  String signerPhone;
    @ApiModelProperty(name = "startDateTime", value = "开始时间" ,example = "2018-09-25")
    private  String startDateTime;
    @ApiModelProperty(name = "endDateTime", value = "截止时间" ,example = "2018-09-25")
    private  String endDateTime;
    @ApiModelProperty(name = "trackingNumber", value = "物流编号" ,example = "")
    private   String trackingNumber;

    public OrderLogisticscQueryConditions() {
    }


    public OrderLogisticscQueryConditions(Integer status, Long orderId, String signer, String signerPhone, String startDateTime, String endDateTime, String trackingNumber) {
        this.status = status;
        this.orderId = orderId;
        this.signer = signer;
        this.signerPhone = signerPhone;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.trackingNumber = trackingNumber;
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

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
