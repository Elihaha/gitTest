package com.bst.goods.service;

import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.SkuInfoQuery;
import com.bst.goods.model.GoodsPageQuery;
import com.bst.goods.model.GoodsSkuSpecValueRequest;
import com.bst.goods.model.GoodsSpuRequest;
import com.bst.goods.model.SkuStockRedis;

import java.util.List;
import java.util.Set;

/**
 * @author lumin
 * @description:
 * @create 2018-09-17 17:30
 **/
public interface GoodsSpuService {

    Result saveGoods(GoodsSpuRequest request);

    Result updateGoods(GoodsSpuRequest request);

    Result queryGoodsById(Long id);

    Result queryGoodsList(GoodsPageQuery query);

    Result updateGoodsStatus(GoodsSpuRequest request);

    Result batchUpdateGoodsStatus(GoodsSpuRequest request);

    void updateBatchBySpuNo(String s, Integer parseInt);

    void saveGoodsSkuAndGoodsSpec(List<GoodsSkuSpecValueRequest> goodsSkuSpecValueList, GoodsSpu spu,List<SkuStockRedis> skuStockRedisList);

    public Set<SkuInfoQuery> getAllSpecSkuInfoBySpuNo(String key);
}
