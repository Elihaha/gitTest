package com.bst.mallh5.model.goods;

import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.modle.goods.SpecInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author lumin
 * @description: 商品详情类
 * @create 2018-09-17 18:47
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsResponse extends GoodsSpu {
    /**
     * 邮费
     */
    private BigDecimal postage;

    /**
     * 商品名
     */
    private  String shopName;
    /**
     * 销售价格
     */
    private BigDecimal sellPrice;
    /**
     * 商品规格表Id
     */
    private List<Long> skuIds;


    private Set<SpecInfo> specInfos;

    public GoodsResponse() {
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public List<Long>  getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(List<Long> skuIds) {
        this.skuIds = skuIds;
    }

    public Set<SpecInfo> getSpecInfos() {
        return specInfos;
    }

    public void setSpecInfos(Set<SpecInfo> specInfos) {
        this.specInfos = specInfos;
    }
}
