package com.bst.goods.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lumin
 * @description:
 * @create 2018-09-18 10:01
 **/
public class GoodsSpuRequest {
    @ApiModelProperty(value = "商品的Id")
    private Long goodsId;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品的名称")
    private String goodsName;
    /**
     * 商品价格
     */
    @ApiModelProperty(value = "商品的价格")
    private BigDecimal goodsPrice;
    /**
     * 商品类型（分类表的主键）
     */
    @ApiModelProperty(value = "商品的类型，传Id")
    private Long categoryId;
    /**
     * 商品库存
     */
    @ApiModelProperty(value = "新设置的商品库存")
    private Integer totalStock;
    /**
     * 商品详情
     */
    @ApiModelProperty(value = "商品的描述")
    private String goodsDetail;
    /**
     * 商品对应的展示图片
     */
    @ApiModelProperty(value = "商品的展示图片路径")
    private List<String> imagesList;

    @ApiModelProperty(value = "商品的状态（1上架，2下架，3删除）")
    private Byte goodsStatus;

    @ApiModelProperty(value = "商品id集合")
    private List<Long> ids;

    @ApiModelProperty(value = "商品的原始库存")
    private Integer oldTotalStock;

    @ApiModelProperty(value = "规格id")
    private List<Long> specId;

    @ApiModelProperty(value = "规格明细")
    private List<GoodsSkuSpecValueRequest> goodsSkuSpecValueList;

   /* @ApiModelProperty(value = "sku的id,价格和新增库存")
    private List<GoodsSkuRequest> goodsSkuRequestList;*/
    public GoodsSpuRequest() {
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Byte getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Byte goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getOldTotalStock() {
        return oldTotalStock;
    }

    public void setOldTotalStock(Integer oldTotalStock) {
        this.oldTotalStock = oldTotalStock;
    }

    public List<Long> getSpecId() {
        return specId;
    }

    public void setSpecId(List<Long> specId) {
        this.specId = specId;
    }

    public List<GoodsSkuSpecValueRequest> getGoodsSkuSpecValueList() {
        return goodsSkuSpecValueList;
    }

    public void setGoodsSkuSpecValueList(List<GoodsSkuSpecValueRequest> goodsSkuSpecValueList) {
        this.goodsSkuSpecValueList = goodsSkuSpecValueList;
    }

}
