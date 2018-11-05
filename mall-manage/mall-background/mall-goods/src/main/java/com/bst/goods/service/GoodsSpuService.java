package com.bst.goods.service;

import com.bst.common.modle.Result;
import com.bst.goods.model.GoodsPageQuery;
import com.bst.goods.model.GoodsSpuRequest;

/**
 * @author lumin
 * @description:
 * @create 2018-09-17 17:30
 **/
public interface GoodsSpuService {

    Result saveGoods(GoodsSpuRequest request);

    Result updateGoods(GoodsSpuRequest request);

    Result queryGoodsById(Long  id);

    Result queryGoodsList(GoodsPageQuery query);

    Result updateGoodsStatus(GoodsSpuRequest request);

    Result batchUpdateGoodsStatus(GoodsSpuRequest request);
}
