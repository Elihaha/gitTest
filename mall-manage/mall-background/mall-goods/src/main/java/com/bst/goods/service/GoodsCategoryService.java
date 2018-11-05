package com.bst.goods.service;

import com.bst.common.entity.goods.GoodsCategory;
import com.bst.common.modle.Result;
import com.bst.goods.model.CategoryPageQuery;

import java.util.List;

/**
 * 商品分类
 *
 * @author zouqiang
 * @create 2018-09-19 11:59
 **/
public interface GoodsCategoryService {

    /*查询商品分类
     * @author zouqiang
     * @date 2018/9/19 12:00
     * @param
     * @return
     */
    public GoodsCategory queryGoodsCategory(Long id);
    //查询所有商品分类

    //查询商品分类列表
   public Result queryGoodsCategoryList(CategoryPageQuery categoryPageQuery);
}
