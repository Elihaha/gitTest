package com.bst.common.modle.order;

import lombok.Builder;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/28 18:06 2018 09
 */
@Builder
public class OrderChildGenerate {

    private String  mainNo ; //主订单编号
    private String   explanation ; // 说明
    private Integer  number ; // 数量
    private String  remarks  ; //  备注
    private String  trackingNumber  ; //  物流编号
    private String  logisticsCompany  ; // 物流公司

    public OrderChildGenerate() {
    }

    public OrderChildGenerate(String mainNo, String explanation, Integer number, String remarks, String trackingNumber, String logisticsCompany) {
        this.mainNo = mainNo;
        this.explanation = explanation;
        this.number = number;
        this.remarks = remarks;
        this.trackingNumber = trackingNumber;
        this.logisticsCompany = logisticsCompany;
    }

    public String getMainNo() {
        return mainNo;
    }

    public void setMainNo(String mainNo) {
        this.mainNo = mainNo;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }
}
