package com.bst.goods.model;

/**
 * @author zouqiang
 * @create 2018-10-31 11:32
 **/
public class SkuStockRedis {

    private String skuNo;

   /**剩余库存差值*/
    private int minus;

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }
}
