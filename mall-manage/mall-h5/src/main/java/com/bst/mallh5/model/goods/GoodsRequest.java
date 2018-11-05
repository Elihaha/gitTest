package com.bst.mallh5.model.goods;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lumin
 * @description:
 * @create 2018-09-21 18:41
 **/
public class GoodsRequest {
    @ApiModelProperty("商品Id")
    private Long goodsId;

    @ApiModelProperty("用户的默认地址（省名）")
    private String address;

    public GoodsRequest() {
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "GoodsRequest{" +
                "goodsId=" + goodsId +
                ", address='" + address + '\'' +
                '}';
    }
}
