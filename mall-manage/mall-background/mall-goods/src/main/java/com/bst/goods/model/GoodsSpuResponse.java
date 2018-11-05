package com.bst.goods.model;

import com.bst.common.entity.goods.GoodsSpu;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author lumin
 * @description:
 * @create 2018-09-19 11:14
 **/
public class GoodsSpuResponse extends GoodsSpu {
    /**
     * 商品价格
     */
    @ApiModelProperty(value = "商品的价格")
    private BigDecimal goodsPrice;
    @ApiModelProperty(value = "操作人的姓名")
    private String operatorName;
    @ApiModelProperty(value = "品类名称")
    private String categoryName;

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
