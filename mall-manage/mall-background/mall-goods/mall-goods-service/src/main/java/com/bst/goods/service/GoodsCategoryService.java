package com.bst.goods.service;

import com.bst.common.entity.goods.GoodsCategory;
import com.bst.common.modle.Result;
import com.bst.goods.model.CategoryQueryResponse;

import java.util.List;

/**
 * 商品分类
 *
 * @author zouqiang
 * @create 2018-09-19 11:59
 **/
public interface GoodsCategoryService {

    /**
     * 查询商品分类
     * @author zouqiang
     * @date 2018/9/19 12:00
     * @param
     * @return
     */
    Result queryGoodsCategory(Long pid);
    //查询所有商品分类
    /**
     * 添加分类
     * @author zouqiang
     * @date 2018/10/26 21:03
     * @param
     * @return
     */

    GoodsCategory addGoodsCategoryByPid(Long pid, String name);

   /**
    * @author zouqiang
    * @date 2018/10/26 21:08
    * @param [id, name]
    * @return [id, name]
    */

   int updateCategoryByPid(Long id, String name);

   /**
    * 删除分类
    * @author zouqiang
    * @date 2018/10/26 21:23
    * @param [id]
    * @return [id]
    */

   Result deleteCategoryByPid(Long id);

    /**递归查询所有分类
     * @author zouqiang
     * @date 2018/10/26 21:08
     * @param [category]
     * @return [category]
     */

    /**递归查询所有分类
     * @author zouqiang
     * @date 2018/10/26 21:08
     * @param [category]
     * @return [category]
     */
    Result queryAllCategory(Long pid);
}
