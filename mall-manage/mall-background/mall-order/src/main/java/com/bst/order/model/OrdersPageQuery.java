package com.bst.order.model;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author zouqiang
 * @create 2018-09-25 10:37
 **/
public class OrdersPageQuery {
    @ApiModelProperty(value = "页数大小")
    private Integer PageSizeKey;

    @ApiModelProperty(value = "当前页数（从1开始）")
    private Integer PageNumKey;

    @ApiModelProperty(value = "查询的订单状态（0未上架，1上架，2下架，3查询全部）")
    private Byte ordersStatus;

    @ApiModelProperty(value = "订单号")
    private String orderNo;//订单号
    @ApiModelProperty(value = "用户名")
    private String consumerName;//用户名
    @ApiModelProperty(value = "电话")
    private String telephone;//电话
    @ApiModelProperty(value = "开始时间")
    private String startUpdate;
    @ApiModelProperty(value = "结束时间")
    private String endUpdate;

    public Integer getPageSizeKey() {
        return PageSizeKey;
    }

    public void setPageSizeKey(Integer pageSizeKey) {
        PageSizeKey = pageSizeKey;
    }

    public Integer getPageNumKey() {
        return PageNumKey;
    }

    public void setPageNumKey(Integer pageNumKey) {
        PageNumKey = pageNumKey;
    }

    public Byte getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(Byte ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getstartUpdate() {
        return startUpdate;
    }

    public void setstartUpdate(String startUpdate) {
        this.startUpdate = startUpdate;
    }

    public String getendUpdate() {
        return endUpdate;
    }

    public void setendUpdate(String endUpdate) {
        this.endUpdate = endUpdate;
    }


  /*
    @ApiModelProperty(value = "页数大小")
    private String goodsName;
    @ApiModelProperty(value = "页数大小")
    private int goodsCount;
    @ApiModelProperty(value = "页数大小")
    private int totalAmount;


    @ApiModelProperty(value = "页数大小")
    private Date createTime;
    @ApiModelProperty(value = "页数大小")
    private Date payTime;
    @ApiModelProperty(value = "页数大小")
    private Date deliveryTime;//发货时间
    @ApiModelProperty(value = "页数大小")
    private Date lastUpdate;*/

    public OrdersPageQuery() {
    }
}
