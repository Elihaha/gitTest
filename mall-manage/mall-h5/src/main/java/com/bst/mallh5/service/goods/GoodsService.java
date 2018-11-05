package com.bst.mallh5.service.goods;

import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsRedisInfo;
import com.bst.common.modle.goods.SkuInfoQuery;
import com.bst.mallh5.model.goods.GoodsRequest;

import java.util.Set;

/**
 * @author lumin
 * @description:
 * @create 2018-09-17 17:30
 **/
public interface GoodsService {

    public Result findGoodsDetailById(GoodsRequest request);

    public Result queryGoodsSkuList(Long spuId);

    public Set<SkuInfoQuery> getAllSpecSkuInfoBySpuNo(String key);

    public  void  delSkuInfoBySpuNo(String key);

    public Result getAllSpuInfoByShopId(Long shopId, Integer start, Integer end) ;

}
