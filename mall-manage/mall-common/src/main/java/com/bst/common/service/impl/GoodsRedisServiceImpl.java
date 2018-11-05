package com.bst.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsRedisInfo;
import com.bst.common.modle.goods.GoodsSkuAndImg;
import com.bst.common.modle.goods.SkuInfoQuery;
import com.bst.common.modle.goods.SpecInfo;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Tuple;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author lumin
 * @description:
 * @create 2018-09-17 17:31
 **/
@Service("goodsRedisService")
@Slf4j
@PropertySource(value = "classpath:application.yml", ignoreResourceNotFound = true)
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class, readOnly = false)
public class GoodsRedisServiceImpl implements GoodsRedisService {

    @Autowired
    RedisParam redisParam;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsRedisServiceImpl.class);

    @Autowired
    private JedisClusterUtils jedisCluster;

    @Autowired
    private GoodsSpuMapper goodsSpuMapper;

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;


    @Autowired
    GoodsRedisService goodsRedisService;


    /**
     * 获取所有的 spu  旗下的所有的缓存
     *
     * @param key
     * @return
     */
    @Override
    public Set<SkuInfoQuery> getAllSpecSkuInfoBySpuNo(String key) {
        //  获取redis  里面  该商品所有的 规格相关的值
        final Map<String, String> stringStringMap = jedisCluster.hgetAll(key);

        if (stringStringMap != null) {
            final Set<SkuInfoQuery> collect = stringStringMap.entrySet().stream().map(stringStringEntry -> {
                final String value = stringStringEntry.getValue();
                if (value != null) {
                    final JSONObject jsonObject = JSONObject.parseObject(value);
                    return SkuInfoQuery.builder()
                            .imgPath(jsonObject.getString("imgPath"))
                            .price(jsonObject.getString("pricing"))
                            .skuNo(jsonObject.getString("skuNo"))
                            .stock(goodsRedisService.getCurrentSkuCountBySkuNO(jsonObject.getString("skuNo")))
                            .info(stringStringEntry.getKey())
                            .build();
                }
                return new SkuInfoQuery();
            }).collect(Collectors.toSet());
            return collect;
        }
        return Collections.EMPTY_SET;
    }

    @Override
    public Result getAllSpuInfoByShopId(Long shopId, Integer start, Integer end) {
        Long count = null;
         Set<GoodsRedisInfo> collect1=null;
        try {
            String key = redisParam.getGoodsShopBaseInfoKey()+shopId+":new";
            String hashKey =RedisParam.hashKey(redisParam.getGoodsSpuBaseInfoKey(),RedisParam.HASH)+shopId;
            count = jedisCluster.zcard(key);
           log.info("start {}  end {}",start,end);
            final Set<Tuple> strings = jedisCluster.zrangeWithScores(key, start,end);
                                      collect1  = strings.stream()
                                                        .map(object ->
                                                        {
                                                            GoodsRedisInfo goodsRedisInfo = JSONObject.parseObject(jedisCluster.get(redisParam.getGoodsSpuBaseInfoKey()+jedisCluster.hget(hashKey, object.getElement()).get()), GoodsRedisInfo.class);
                                                            //   判断当前商品是否已  售空
                                                            goodsRedisInfo.setSoldOut((goodsRedisService.getCurrentSPuCountBySpuNO(goodsRedisInfo.getSpuNo())>0?false:true));
                                                            goodsRedisInfo.setTotalStock(null);
                                                            goodsRedisInfo.setOperatorId(null);
                                                            goodsRedisInfo.setSkuIds(null);
                                                            goodsRedisInfo.setSpecInfos(null);

                                                            return goodsRedisInfo;
                                                        })
                                                        .collect(Collectors.toSet());
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage(),e);
            return   Result.error(e.getMessage());
        }
        return   Result.ok().put("count",count).put("info",collect1);
    }


    public void addSkuInfoBySpuNo(String key, Map<String, String> second) {
        second.entrySet().stream().forEach(stringStringEntry -> {
            jedisCluster.hset(key, stringStringEntry.getKey(), stringStringEntry.getValue());
        });
    }

    /**
     *
     * @param key
     * @param second  规则  1:2:3
     * @param value
     * @return
     */
    @Override
    public boolean addSkuInfoBySpuNo(String key, String second, String value) {
        try {
            //   TODO    事物回滚
            jedisCluster.hset(RedisParam.hashKey(redisParam.getKeyGoodsskuAll(),RedisParam.HASH)+key, second, value);
//            jedisCluster.zadd(RedisParam.hashKey(key,RedisParam.SET), LocalDateTime.now().toInstant((ZoneOffset.of("+8"))).toEpochMilli(),value);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage(),e);
            return  false ;
        }

    }

    @Override
    public boolean addCountBySkuNO(String skuNO) {
        Long decr = null;
        try {
            if (StringUtils.isBlank(skuNO)) {
                throw new RuntimeException("skuNO  is  null");
            }
            String keyGoodssku = redisParam.getKeyGoodssku();
            String key = skuNO.startsWith(keyGoodssku) ? skuNO : keyGoodssku + skuNO;
            decr = jedisCluster.hincr(key, GoodsSpu.STOCK);
            log.info("addCountBySkuNO  {}", decr);
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage(), e);
            return false;
        }
        boolean b = decr != -1;
        return b;
    }

    @Override
    public boolean addCountBySkuNO(String skuNO, Integer number) {
        Long decr = null;
        try {
            if (StringUtils.isBlank(skuNO)) {
                throw new RuntimeException("skuNO  is  null");
            }
            if (number < 0) {
                throw new RuntimeException("number  is  0  如果要减少  请调 delCountBySkuNO ");
            }
            String key = RedisParam.hashKey(redisParam.getKeyGoodssku(),RedisParam.HASH)+skuNO;
            decr = jedisCluster.hincrBy(key, GoodsSpu.STOCK, number);
            log.info("addCountBySkuNO  redis-key:【{}】， number {}", key, decr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        boolean b = decr != -1;
        return b;
    }

    @Override
    /**
     *   默认减一个
     */
    public boolean delCountBySkuNO(String skuNO) {
        Long decr = null;
        try {
            if (StringUtils.isBlank(skuNO)) {
                throw new RuntimeException("skuNO  is  null");
            }
            String keyGoodssku = redisParam.getKeyGoodssku();
            String key = skuNO.startsWith(keyGoodssku) ? skuNO : keyGoodssku + skuNO;
            decr = jedisCluster.hdecrBy(key, GoodsSpu.STOCK);
            log.info("delCountBySkuNO  {}", decr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        //  暂时超库处理
        if (decr < 0) {
            addCountBySkuNO(skuNO);
        }
        boolean b = decr != -1;
        return b;
    }

    @Override
    /**
     * @Param skuNo
     * @Param number  一次性的数量
     */
    public boolean delCountBySkuNO(String skuNO, Integer number) {
        Long decr = null;
        try {
            if (StringUtils.isBlank(skuNO)) {
                throw new RuntimeException("skuNO  is  null");
            }
            if (number < 0) {
                throw new RuntimeException("number  is  0 如果要增加  请调 addCountBySkuNO ");
            }
            String key = redisParam.getKeyGoodssku() + skuNO;
            decr = jedisCluster.hdecrBy(key, GoodsSpu.STOCK, number);
            log.info("delCountBySkuNO  入参key前缀:【{}】入参编号：【{}】，增加数量【{}】，结果【{}】", key, skuNO, number, decr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        //  暂时超库处理
        if (decr < 0) {
            addCountBySkuNO(skuNO, number);
        }
        boolean b = decr > -1;

        return b;
    }

    @Override
    public Integer getCurrentSkuCountBySkuNO(String skuNO) {
        try {
            if (StringUtils.isBlank(skuNO)) {
                throw new RuntimeException("skuNO  is  null");
            }
            Integer integer = Integer.valueOf(jedisCluster.hget(RedisParam.hashKey(redisParam.getKeyGoodssku(),RedisParam.HASH) + skuNO,GoodsSpu.STOCK).orElse(null));
            log.info("getCurrentSkuCountBySkuNO  入参：【{}】，结果【{}】", skuNO, integer);
            return integer;
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    /**
     * 根据 spuNO  增加 redis当前spu 数量
     *
     * @param spuNO
     * @return
     */
    @Override
    public boolean addCountBySpuNO(String spuNO) {
        Long decr = null;
        try {
            if (StringUtils.isBlank(spuNO)) {
                throw new RuntimeException("spuNO  is  null");
            }
            decr = jedisCluster.hincr(redisParam.getKeyGoodsspu() + spuNO, GoodsSpu.STOCK);
            log.info("addCountBySpuNO  {}", decr);
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage(), e);
            return false;
        }
        boolean b = decr != -1;
        return b;
    }

    /**
     * 根据 spuNO  增加 redis当前spu 数量
     *
     * @param spuNO
     * @param number
     * @return
     */
    @Override
    public boolean addCountBySpuNO(String spuNO, Integer number) {
        Long decr = null;
        try {
            if (StringUtils.isBlank(spuNO)) {
                throw new RuntimeException("spuNO  is  null");
            }
            if (number < 0) {
                throw new RuntimeException("number  is  0 如果要减少  请调 delCountBySpuNO ");
            }
            String key = RedisParam.hashKey(redisParam.getKeyGoodsspu(),RedisParam.HASH) + spuNO;
            decr = jedisCluster.hincrBy(key, GoodsSpu.STOCK, number);
            log.info("addCountBySpuNO 入参key:【{}】，入参编号：【{}】，增加数量【{}】，结果【{}】", key, spuNO, number, decr);
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage(), e);
            return false;
        }
        boolean b = decr != -1;
        return b;
    }

    /**
     * 根据 spuId     redis 当前spu 数量 减1
     *
     * @param spuNO
     * @return
     */
    @Override
    public boolean delCountBySpuNO(String spuNO) {
        Long decr = null;
        try {
            if (StringUtils.isBlank(spuNO)) {
                throw new RuntimeException("spuNO  is  null");
            }
            decr = jedisCluster.hdecrBy(redisParam.getKeyGoodsspu() + spuNO, GoodsSpu.STOCK);
            log.info("delCountBySpuNO   入参编号：【{}】,结果【{}】", spuNO, decr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        //  暂时超库处理
        if (decr < 0) {
            addCountBySkuNO(spuNO);
        }
        boolean b = decr > -1;

        return b;
    }

    /**
     * 根据 spuId  减 redis当前 spu 数量
     *
     * @param spuNO
     * @param number
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delCountBySpuNO(String spuNO, Integer number) {
        Long decr = null;
        try {
            if (StringUtils.isBlank(spuNO)) {
                throw new RuntimeException("spuNO  is  null");
            }
            if (number < 0) {
                throw new RuntimeException("number  is  0  如果要增加  请调 addCountBySpuNO ");
            }
            String key = redisParam.getKeyGoodsspu() + spuNO;
            decr = jedisCluster.hdecrBy(key, GoodsSpu.STOCK, number);
            log.info("delCountBySpuId   入参编号：【{}】，减少数量【{}】，结果【{}】", key, number, decr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        //  暂时超库处理
        if (decr < 0) {
            addCountBySpuNO(spuNO, number);
        }
        AtomicBoolean b = new AtomicBoolean(decr > -1);
        return b.get();
    }

    /**
     * 根据 spuId  查询 redis当前spu 数量
     *
     * @param spuNO
     * @return
     */
    @Override
    public Integer getCurrentSPuCountBySpuNO(String spuNO) {
        try {
            String key = RedisParam.hashKey(redisParam.getKeyGoodsspu(),RedisParam.HASH) + spuNO;
            Integer integer = Integer.valueOf(jedisCluster.hget(key,GoodsSpu.STOCK).orElse(null));
            log.info("getCurrentSPuCountBySpuNO   入参：spuNo:【{}】，key:【{}】，结果【{}】", spuNO, key, integer);
            return integer;
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    /**
     * @param name   城市名字
     * @param shopId 商户id
     * @return
     */
    @Override
    public BigDecimal getCurrentPostageMerchant(String name, Long shopId) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("name  is  null");
        }
        if (shopId < 0) {
            throw new RuntimeException("goodsId  < 0 ");
        }
        Optional<String> stringOptional = jedisCluster.hget(redisParam.getPostageConfig() + shopId, name);

        String hget = stringOptional.isPresent()?stringOptional.get():"0";
        return StringUtils.isNotBlank(hget) ? new BigDecimal(hget) : new BigDecimal(0.0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long updateMysqlSpuStockBySpuNo(String spuNo, Integer number) {
        Integer finalParseInt = number;
        HashMap<String, Object> map = new HashMap<String, Object>() {{
            put("spuNo", spuNo);
            put("number", finalParseInt);
        }};
        goodsSpuMapper.updateBatchBySpuNo(map);
        Object parseInt = map.get("number");
        number = (Integer) parseInt;
        return number;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMysqlSkuStockBySkuNo(String skuNo, Integer number) {
        Integer finalParseInt = number;
        HashMap<String, Object> map = new HashMap<String, Object>() {{
            put("skuNo", skuNo);
            put("number", finalParseInt);
        }};
        goodsSkuMapper.updateBatchBySkuNo(map);
        Object parseInt = map.get("number");
        number = (Integer) parseInt;
        return number;
    }















    @Override
    public  void setGoodsSkuAndImgs(String spuNo , Long shopId) {
        List<GoodsSkuAndImg> goodsSkus = goodsSkuMapper.selectGoodsSkuAllAndImg(spuNo,shopId);
        goodsSkus = goodsSkus.stream().filter(goodsSku -> goodsSku.getSkuNo() != null && goodsSku.getStock() != null && goodsSku.getImgPath() != null && goodsSku.getSpuNo() != null).collect(Collectors.toList());

        //    有图片 的sku 信息
        goodsSkus.stream().forEach(goodsSku -> {

            ///   更新  sku 库存

            final String skuNo = goodsSku.getSkuNo();
            final Integer stock = goodsSku.getStock();
            final Byte skuStatus = goodsSku.getSkuStatus();
            final Integer soldoutCount = goodsSku.getSoldoutCount();

            setSpecificationcCacheSku(goodsSku);

            updateSkuStatis(skuNo, skuStatus);

            updateSkuStockCount(skuNo,stock);

            updateSkuSoldoutCount(skuNo, soldoutCount);

        });
    }

    public  void updateSkuSoldoutCount(String skuNo, Integer soldoutCount) {
        // 更新销量
        jedisCluster.hset(RedisParam.hashKey(redisParam.getKeyGoodssku(),RedisParam.HASH) + skuNo, GoodsSpu.SALES_VOLUME, soldoutCount);
    }

    /**
     *   修改 数据库  sku 库存
     */

    public void updateSkuStockCount(String skuNo, Integer stock) {
        addCountBySkuNO( skuNo, stock);
    }



    /**
     *          // 更新状态
     * @param skuNo
     */
    public void updateSkuStatis( String skuNo,byte b) {
        jedisCluster.hset(RedisParam.hashKey(redisParam.getKeyGoodssku(),RedisParam.HASH)+ skuNo, GoodsSpu.STATUS,b);
    }


    /**
     *          // 更新状态
     * @param skuNo
     */
    public byte getSkuStatis( String spuNo) {
        return Byte.valueOf(jedisCluster.hget(RedisParam.hashKey(redisParam.getKeyGoodssku(),RedisParam.HASH) + spuNo, GoodsSpu.STATUS).get()) ;
    }





    /**
     *          // 更新状态
     * @param skuNo
     */
    public void updateSpuStatis( String spuNo,byte b) {
        jedisCluster.hset(RedisParam.hashKey(redisParam.getKeyGoodsspu(),RedisParam.HASH) + spuNo, GoodsSpu.STATUS,b);
    }


    /**
     *          // 更新状态
     * @param skuNo
     */
    public byte getSpuStatis( String spuNo) {
        return Byte.valueOf(jedisCluster.hget(RedisParam.hashKey(redisParam.getKeyGoodsspu(),RedisParam.HASH) + spuNo, GoodsSpu.STATUS).get()) ;
    }




    /**
     *   修改 数据库  sku 库存
     */

    public void updateSpuStockCount(String skuNo, Integer stock) {
        addCountBySpuNO(skuNo, stock);
    }


    public  void updateSpuSoldoutCount(String skuNo, Integer soldoutCount) {
        // 更新销量
        jedisCluster.hset(RedisParam.hashKey(redisParam.getKeyGoodsspu(),RedisParam.HASH) + skuNo, GoodsSpu.SALES_VOLUME, soldoutCount);
    }



    /**
     *   设置 根据规格缓存 sku
     * @param goodsSku
     */
    private void setSpecificationcCacheSku(GoodsSkuAndImg goodsSku) {
        final List<String> collect = goodsSku.getGoodsSpecValueEntity().stream().map(goodsSpecValueEntity -> goodsSpecValueEntity.getId()+"").collect(Collectors.toList());
         String join = String.join(RedisParam.COLON, collect);
        if (StringUtils.isBlank(join)){
            join="-1";
        }
        goodsRedisService.addSkuInfoBySpuNo(goodsSku.getSpuNo(),join , goodsSku.getInfo());
    }





    public  void setGoodsRedisInfos(Long shopId, String spuNo) {
        //     更新  spu 库存  spu 缓存
        List<GoodsRedisInfo> goodsSpus = goodsSpuMapper.selectRedisInfoByPrimaryKey(spuNo,shopId);
        goodsSpus = goodsSpus.stream().filter(goodsSku -> goodsSku.getSpuNo() != null && goodsSku.getTotalStock() != null&&goodsSku.getSpecInfos()!=null)
                .map(goodsRedisInfo -> {
                    final String next = goodsRedisInfo.getImagesList().iterator().next();
                    final List<String> strings = JSONArray.parseArray(next, String.class);
                    goodsRedisInfo.setImagesList(new HashSet<>(strings));
                    return goodsRedisInfo;
                })
                .collect(Collectors.toList());
        goodsSpus.forEach(goodsSpu -> {
            spuStock(goodsSpu);

            addSpuCache(goodsSpu);
        });
    }



    public void spuStock(GoodsSpu goodsSpu){
        final Integer totalStock = goodsSpu.getTotalStock();
        final String spuNo = goodsSpu.getSpuNo();
        addCountBySpuNO(spuNo,(totalStock));
    }



    public  void addSpuCache(GoodsRedisInfo goodsSpu) {
        for (SpecInfo specInfo : goodsSpu.getSpecInfos()) {
             if(specInfo==null){
                 return;
             }
        }
        String key1 = redisParam.getGoodsSpuBaseInfoKey()+goodsSpu.getId();
        System.out.println(key1);
        //  TODO  兼容 一期 h5 缓存 商品信息 的  旧代码
        jedisCluster.set(key1, JSON.toJSONString(goodsSpu));
        String   key = redisParam.getGoodsShopBaseInfoKey()+goodsSpu.getShopId();

        //   商品信息 首页缓存  支持分页
        final String spuNo = goodsSpu.getSpuNo();

        jedisCluster.zadd(key+":new", Long.valueOf(LocalDateTime.now().toInstant((ZoneOffset.of("+8"))).toEpochMilli()).doubleValue(), spuNo);

        updateSpuStatis(spuNo,goodsSpu.getGoodsStatus());

        updateSpuStockCount(spuNo,goodsSpu.getTotalStock());

        updateSpuSoldoutCount(spuNo, goodsSpu.getSoldoutCount());

        jedisCluster. hset(RedisParam.hashKey(redisParam.getGoodsSpuBaseInfoKey(),RedisParam.HASH)+goodsSpu.getShopId(), spuNo,key1);
    }

}
