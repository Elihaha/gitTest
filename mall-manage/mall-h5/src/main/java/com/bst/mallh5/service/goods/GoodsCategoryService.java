package com.bst.mallh5.service.goods;

import com.bst.common.entity.goods.GoodsCategory;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsSpuCategoryResponse;

import java.util.List;

/**
 * @author zouqiang
 * @create 2018-11-01 10:18
 **/
public interface GoodsCategoryService {

    Result queryGoodsCategory(Long pid, Long shopId);

    Result querySpuByCategoryId(Long id, Long shopId, Integer page, Integer rows);

    Result queryAllSpuByShopId(Long ShopId,Integer page, Integer rows);

    Result queryAllCategory(Long pid,Long shopId);
}
