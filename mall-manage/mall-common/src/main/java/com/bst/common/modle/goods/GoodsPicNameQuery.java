package com.bst.common.modle.goods;

import java.math.BigDecimal;

/**
 * @author zouqiang
 * @create 2018-09-27 0:15
 **/
public class GoodsPicNameQuery {


    private String goodsName;
    private String imageUrl;
    private BigDecimal sellPrice;
    private String shopName;
    //规格
    private String pecValue;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPecValue() {
        return pecValue;
    }

    public void setPecValue(String pecValue) {
        this.pecValue = pecValue;
    }
}
