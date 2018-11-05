package com.bst.common.service;

import com.bst.common.modle.Result;
import com.bst.common.modle.goods.SkuInfoQuery;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * @author lumin
 * @description:
 * @create 2018-09-17 17:30
 **/
public interface GoodsRedisService {




    /**
     *    根據spu  生成 规格值  加   sku  信息   设置
     * @param key
     * @param second
     */

    public void addSkuInfoBySpuNo(String key, Map<String, String> second);

    public boolean addSkuInfoBySpuNo(String key, String second, String value);

    /**
     * 将 redis  里面新增 sku  库存
     *
     * @param skuNO
     * @return
     */
    public boolean addCountBySkuNO(String skuNO);

    public boolean addCountBySkuNO(String skuNO, Integer number);

    /**
     * 减  redis sku  库存
     *
     * @param skuNO
     * @return
     */
    public boolean delCountBySkuNO(String skuNO);

    public boolean delCountBySkuNO(String skuNO, Integer number);


    public Integer getCurrentSkuCountBySkuNO(String skuNO);






    public boolean addCountBySpuNO(String spuNO);

    public boolean addCountBySpuNO(String spuNO, Integer number);

    /**
     * 减  redis spu  库存
     *
     * @param spuNO
     * @return
     */
    public boolean delCountBySpuNO(String spuNO);

    public boolean delCountBySpuNO(String spuNO, Integer number);

    public Integer getCurrentSPuCountBySpuNO(String spuNO);

    public BigDecimal getCurrentPostageMerchant(String name, Long shopId);


    public long updateMysqlSpuStockBySpuNo(String spuNo, Integer number);

    public int updateMysqlSkuStockBySkuNo(String spuNo, Integer number);










    /**
     *    跟新  规格缓存 的  规格sku
     * @param spuNo   f非必须
     * @param shopId   非必须
     */
    public  void setGoodsSkuAndImgs(String spuNo, Long shopId);

    public  void updateSkuSoldoutCount(String skuNo, Integer soldoutCount);
    public void updateSkuStatis( String skuNo,byte b);
    public byte getSkuStatis( String spuNo);
    public void updateSkuStockCount(String skuNo, Integer stock);

    /**
     *    跟新  商品信息缓存 的  spu
     * @param spuNo   f非必须
     * @param shopId   非必须
     */
    public  void setGoodsRedisInfos(Long shopId, String spuNo);

    public  void updateSpuSoldoutCount(String skuNo, Integer soldoutCount);
    public void updateSpuStatis( String skuNo,byte b);
    public byte getSpuStatis( String spuNo);
    public void updateSpuStockCount(String skuNo, Integer stock);

    public Set<SkuInfoQuery> getAllSpecSkuInfoBySpuNo(String key);
    public Result getAllSpuInfoByShopId(Long shopId, Integer start, Integer end);


}
