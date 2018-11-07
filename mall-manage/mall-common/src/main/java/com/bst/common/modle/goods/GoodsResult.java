package com.bst.common.modle.goods;

import com.bst.common.entity.goods.GoodsSpu;

/**
 * @author lumin
 * @description:
 * @create 2018-09-19 15:45
 **/
public class GoodsResult extends GoodsSpu {
    /**
     * 类型
     */
    private String categoryName;
    /**
     * 生于库存
     */
    private Integer lastStock;
    /**
     * 操作人
     */
    private String operatorName;
    /**
     * 父分类名
    * */
    private String parentName;
    /**
     * 商户后台商品列表查询价格
     */

    private String price;

    /**商品图片地址*/
    private String imageUrl;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getLastStock() {
        return lastStock;
    }

    public void setLastStock(Integer lastStock) {
        this.lastStock = lastStock;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
