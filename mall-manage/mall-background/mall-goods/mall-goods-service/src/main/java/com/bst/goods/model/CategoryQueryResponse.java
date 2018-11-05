package com.bst.goods.model;

import com.bst.common.entity.goods.GoodsCategory;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author zouqiang
 * @create 2018-09-25 15:21
 **/
public class CategoryQueryResponse{

    private Long id;

    private String categoryName;

   private List<CategoryQueryResponse> catQueryList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryQueryResponse> getCatQueryList() {
        return catQueryList;
    }

    public void setCatQueryList(List<CategoryQueryResponse> catQueryList) {
        this.catQueryList = catQueryList;
    }
}
