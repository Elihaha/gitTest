package com.bst.common.modle.order;

import com.alibaba.fastjson.JSON;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.entity.order.OrderLogisticsEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@ApiModel
public class OrderLogisticsUpdateDto {


    @ApiModelProperty(name = "orderNo", value = "orderNo", example = "1000001709357141", dataType = "long", required = true)
    private String orderNo;
    //

    //
    @ApiModelProperty(name = "dhl", value = "快递公司", example = "zhaijisong", dataType = "string", required = true)
    private String dhl;
    //
    @ApiModelProperty(name = "trackingNumber", value = "相关物流信息编号", example = "889999834560284200", dataType = "string", required = true)
    private String trackingNumber;


    public OrderLogisticsUpdateDto() {
    }

    public OrderLogisticsUpdateDto(String orderNo, String dhl, String trackingNumber) {
        this.orderNo = orderNo;
        this.dhl = dhl;
        this.trackingNumber = trackingNumber;
    }


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
