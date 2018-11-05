package com.bst.goods.model;

import java.math.BigDecimal;

/**
 * @author zouqiang
 * @create 2018-10-31 10:23
 **/
public class GoodsSkuRequest {

    /**
     * skuId
     */
    private String skuNo;

    /**
     * sku价格
     */
    private BigDecimal skuPrice;

    /**
     * sku库存
     */
    private Integer skuTotalStock;

    /**
     *sku旧库存
     * @param
     * @return
     */

    private Integer skuOldTotalStock;

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Integer getSkuTotalStock() {
        return skuTotalStock;
    }

    public void setSkuTotalStock(Integer skuTotalStock) {
        this.skuTotalStock = skuTotalStock;
    }

    public Integer getSkuOldTotalStock() {
        return skuOldTotalStock;
    }

    public void setSkuOldTotalStock(Integer skuOldTotalStock) {
        this.skuOldTotalStock = skuOldTotalStock;
    }
}
