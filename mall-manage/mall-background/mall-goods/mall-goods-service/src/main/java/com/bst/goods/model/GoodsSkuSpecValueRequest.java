package com.bst.goods.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * 规格明细实体
 * @author zouqiang
 * @create 2018-10-30 15:21
 **/
public class GoodsSkuSpecValueRequest {

    //规格明细中规格值ID
    private List<Long> list;

    //sku规格价钱
    private BigDecimal goodsSkuPrice;

    //新sku库存
    private Integer goodsSkuTotalStock;

    private String skuNo;

    /**sku旧库存*/
    private Integer skuOldTotalStock;

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    public BigDecimal getGoodsSkuPrice() {
        return goodsSkuPrice;
    }

    public void setGoodsSkuPrice(BigDecimal goodsSkuPrice) {
        this.goodsSkuPrice = goodsSkuPrice;
    }

    public Integer getGoodsSkuTotalStock() {
        return goodsSkuTotalStock;
    }

    public void setGoodsSkuTotalStock(Integer goodsSkuTotalStock) {
        this.goodsSkuTotalStock = goodsSkuTotalStock;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public Integer getSkuOldTotalStock() {
        return skuOldTotalStock;
    }

    public void setSkuOldTotalStock(Integer skuOldTotalStock) {
        this.skuOldTotalStock = skuOldTotalStock;
    }
}
