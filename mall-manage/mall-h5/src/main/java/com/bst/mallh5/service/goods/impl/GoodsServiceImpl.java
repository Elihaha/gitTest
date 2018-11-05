package com.bst.mallh5.service.goods.impl;

import com.alibaba.fastjson.JSON;
import com.bst.common.constants.CommonConstants;
import com.bst.common.constants.GoodsConstants;
import com.bst.common.entity.goods.GoodsImage;
import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSkuExample;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.mapper.goods.GoodsImageMapper;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.mapper.order.PostageConfigDao;
import com.bst.common.mapper.shop.ShopInfoMapper;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsRedisInfo;
import com.bst.common.modle.goods.SkuInfoQuery;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.mallh5.model.goods.GoodsRequest;
import com.bst.mallh5.model.goods.GoodsResponse;
import com.bst.mallh5.service.goods.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author lumin
 * @description:
 * @create 2018-09-17 17:31
 **/
@Service("goodsService")
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private JedisClusterUtils jedisCluster;

    @Autowired
    private GoodsSpuMapper goodsSpuMapper;

    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Autowired
    private ShopInfoMapper shopInfoMapper;

    @Autowired
    private PostageConfigDao postageConfigDao;

    @Autowired
    private  GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private GoodsRedisService goodsRedisService;

//    @Value("${redisKey.config.mallGoodsSpuBaseInfo}")
//    private String goodsSpuBaseInfoKey;

    @Autowired
    RedisParam redisParam;
    /**
     * 根据Id,查询商品详情
     * @param request
     * @return
     */
    @Override
    public Result findGoodsDetailById(GoodsRequest request) {
        try{
            Long goodsId = request.getGoodsId();
            GoodsResponse response = new GoodsResponse();
            //先从redis中取，如果没有商品详情，就将从数据库查，然后存入redis
            //商品信息从redis中获取
            String key = redisParam.getGoodsSpuBaseInfoKey()+goodsId;

            //判断商品库存
            final Boolean aBoolean = jedisCluster.existsObject(key);
            if(!aBoolean){
                //商品并没有存入redis中
                GoodsSpu spu = goodsSpuMapper.selectByPrimaryKey(goodsId);
                if (spu == null) {
                    return Result.error("该商品不存在或已下架！");
                }
                addSpuCache(spu);
            }

            GoodsRedisInfo goodsSpu  = JSON.parseObject(jedisCluster.get(key),GoodsRedisInfo.class);
            //从redis查询商品总剩余的库存值
            Integer stock = goodsRedisService.getCurrentSPuCountBySpuNO(goodsSpu.getSpuNo());
            goodsSpu.setTotalStock(stock);
            BeanUtils.copyProperties(goodsSpu,response);
            response.setSellPrice(goodsSpu.getHighPrice());

            response.setGoodsStatus(null);
            response.setOperatorId(null);
            response.setSellPrice(null);

            //获取运费
            if(StringUtils.isBlank(request.getAddress())){
                response.setPostage(BigDecimal.valueOf(0));
            }else{
                String address = request.getAddress();
                BigDecimal postage = goodsRedisService.getCurrentPostageMerchant(address, goodsSpu.getShopId());
                response.setPostage(postage==null ? BigDecimal.valueOf(0) : postage);
            }
            Map<String, Object> result = new HashMap<String,Object>();
            result.put("goodsDetail",response);
            result.put("imagesList",goodsSpu.getImagesList());
            //判断商品库存
            final Integer currentSPuCountBySpuNO = goodsRedisService.getCurrentSPuCountBySpuNO(goodsSpu.getSpuNo());
            final byte spuStatis = goodsRedisService.getSpuStatis(goodsSpu.getSpuNo());
            if(currentSPuCountBySpuNO <1 || spuStatis != (byte) CommonConstants.goodsOnShelfStatus){
                //库存少于1，返回当前商品已售空
                return Result.ok(GoodsConstants.GOODS_SOLD_OUT.getStatus(),GoodsConstants.GOODS_SOLD_OUT.getMessage(), result);
            }
            return Result.ok(result);
        }catch (Exception e){
            LOGGER.error(">>>>>>>查询商品详情失败，异常：",e);
            return Result.error("该商品不存在或已下架！");
        }
    }

    public  void addSpuCache(  GoodsSpu spu) {
//        String key = redisParam.getGoodsSpuBaseInfoKey()+":"+spu.getId();
//        BeanUtils.copyProperties(spu,goodsSpu);
//        goodsSpu.setShopName(shopInfoMapper.selectByPrimaryKey(spu.getShopId()).getShopName());
//        List<Long> skuIds = goodsSkuMapper.getSkuIdsBySpuId(spu.getId());
//        goodsSpu.setSkuIds(new HashSet<>(skuIds));
//        //获取商品的展示照片
//        GoodsImage recode = new GoodsImage();
//        recode.setMainId(goodsSpu.getId());
//        recode.setImageType(1);
//        List<String> imageList = goodsImageMapper.selectByRecode(recode);
//        goodsSpu.setImagesList(new HashSet<>(imageList));
        goodsRedisService.setGoodsRedisInfos(spu.getShopId(),spu.getSpuNo());
//        jedisCluster.set(key, JSON.toJSONString(goodsSpu));
    }

    /**
     * 查询规格集合（每个规格的集合）
     * @param spuId
     * @return
     */
    @Override
    public Result queryGoodsSkuList(Long spuId) {
        try {
            //sku规格Id
            GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
            goodsSkuExample.createCriteria().andSpuIdEqualTo(spuId);
            List<GoodsSku> skus = goodsSkuMapper.selectByExample(goodsSkuExample);
            ListIterator<GoodsSku> iterator = skus.listIterator();
            while (iterator.hasNext()){
                GoodsSku sku = iterator.next();
                Integer stock = goodsRedisService.getCurrentSkuCountBySkuNO(sku.getSkuNo());
                sku.setStock(stock);
            }
            return Result.ok(skus);
        }catch (Exception e){
            LOGGER.error(">>>>>>>查询商品所有规格，异常：",e);
            return Result.error("查询失败：系统异常");
        }
    }
    @Override
    public Set<SkuInfoQuery> getAllSpecSkuInfoBySpuNo(String key) {
        return goodsRedisService.getAllSpecSkuInfoBySpuNo(RedisParam.hashKey(redisParam.getKeyGoodsskuAll(),RedisParam.HASH)+key);
    }

    public  void  delSkuInfoBySpuNo(String key){
          jedisCluster.delkeyObject(redisParam.getKeyGoodsskuAll()+key) ;
    }

    @Override
    public Result getAllSpuInfoByShopId(Long shopId, Integer start, Integer end) {
        return goodsRedisService.getAllSpuInfoByShopId(shopId,start,end);
    }
}
