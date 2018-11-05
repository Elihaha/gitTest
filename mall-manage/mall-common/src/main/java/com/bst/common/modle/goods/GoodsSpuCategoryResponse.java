package com.bst.common.modle.goods;

import java.math.BigDecimal;

/**
 * @author zouqiang
 * @create 2018-11-01 13:45
 **/
public class GoodsSpuCategoryResponse {

    //商品ID
    private Long spuId;

    //商品编号
    private String spuNo;
    //图片地址
    private String imageUrl;

    //商品名称
    private String goodsName;

    //最低价
    private BigDecimal lowPrice;

    //最高价
    private BigDecimal highPrice;

    //销量
    private Integer soldoutCount;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getSpuNo() {
        return spuNo;
    }

    public void setSpuNo(String spuNo) {
        this.spuNo = spuNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getSoldoutCount() {
        return soldoutCount;
    }

    public void setSoldoutCount(Integer soldoutCount) {
        this.soldoutCount = soldoutCount;
    }
}
