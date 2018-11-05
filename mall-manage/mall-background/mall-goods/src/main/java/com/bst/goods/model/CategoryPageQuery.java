package com.bst.goods.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zouqiang
 * @create 2018-09-25 15:21
 **/
public class CategoryPageQuery {

    @ApiModelProperty(value = "页数大小")
    private Integer PageSizeKey;

    @ApiModelProperty(value = "当前页数（从1开始）")
    private Integer PageNumKey;

    @ApiModelProperty(value = "类品的名称")
    private String categoryName;

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


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



}
