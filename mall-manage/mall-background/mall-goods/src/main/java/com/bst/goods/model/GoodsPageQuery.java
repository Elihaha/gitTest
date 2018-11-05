package com.bst.goods.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lumin
 * @description:
 * @create 2018-09-19 13:39
 **/
public class GoodsPageQuery {
    @ApiModelProperty(value = "页数大小")
    private Integer PageSizeKey;

    @ApiModelProperty(value = "当前页数（从1开始）")
    private Integer PageNumKey;

    @ApiModelProperty(value = "查询的商品状态（0未上架，1上架，2下架，3查询全部）")
    private Byte goodsStatus;

    @ApiModelProperty(value = "商品的名称")
    private String goodsName;

    public Integer getPageSizeKey() {
        return PageSizeKey;
    }

    public void setPageSizeKey(Integer pageSizeKey) {
        PageSizeKey = pageSizeKey;
    }

    public Integer getPageNumKey() {
        return PageNumKey;
    }

    public void setPageNumKey(Integer pageNumKey) {
        PageNumKey = pageNumKey;
    }

    public Byte getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Byte goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public GoodsPageQuery() {
    }
}
