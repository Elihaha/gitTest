package com.bst.common.modle.goods;

import com.alibaba.fastjson.JSON;
import com.bst.common.entity.goods.GoodsSpu;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lumin
 * @description:
 * @create 2018-09-28 11:09
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class GoodsRedisInfo extends GoodsSpu {
     // 售罄
    private  boolean soldOut;

    /**
     * 商品名
     */
    private  String shopName;

    private Set<String> imagesList;

    private Set<SpecInfo> specInfos;



    @JsonSerialize(using = ToStringSerializer.class)
    private Set<Long> skuIds;


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Set<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(Set<String> imagesList) {
        this.imagesList = imagesList;
    }

    public Set<Long> getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(Set<Long> skuIds) {
        this.skuIds = skuIds;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public Set<SpecInfo> getSpecInfos() {
        return specInfos;
    }

    public void setSpecInfos(Set<SpecInfo> specInfos) {
        this.specInfos = specInfos;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
