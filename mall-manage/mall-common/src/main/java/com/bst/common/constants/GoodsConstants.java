package com.bst.common.constants;

/**
 * @author lumin
 * @description:
 * @create 2018-09-18 14:47
 **/
public enum GoodsConstants {
    GOODS_COUNTS_FULL(10000,"当前商品已下架"),
    GOODS_SOLD_OUT(10001,"当前商品已售罄");

    private Integer status;
    private String message;

    GoodsConstants(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    GoodsConstants() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
