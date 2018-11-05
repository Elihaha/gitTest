package com.bst.scheduled.task;

import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.goods.service.GoodsSpuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/26 14:55 2018 09
 */
@Component
@Slf4j
public class GoodsScheduledTask {

    @Autowired
    JedisClusterUtils jedisCluster;

    @Autowired
    RedisParam redisParam;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsSpuService goodsSpuMapper;

    /**
     * 同步商品spu库存到mysql
     */
    @Scheduled(fixedDelay = 3 * 60 * 1000)
    public void synchronizeGoodsSpuStock() {
        jedisCluster.keysStream(RedisParam.hashKey(redisParam.getKeyGoodsspu(), RedisParam.HASH), c -> {
            c.parallelStream().forEach(s -> {
                if (RedisParam.HASH.equals(jedisCluster.type(s))) {
                    boolean b = listToMap(s, (key, value) -> {
                        int parseInt = Integer.parseInt(value);
                        if (parseInt > 0) {
                            goodsSpuMapper.updateBatchBySpuNo(key, parseInt);
                        } else {
                            log.error(" spu  id   {}  number is  {}", key, value);
                        }

                    });
                }
            });
        });
    }


    /**
     * 同步商品Sku库存到mysql
     */
    @Scheduled(fixedDelay = 3 * 60 * 1000)
    public void synchronizeGoodsSkuStock() {

        jedisCluster.keysStream(RedisParam.hashKey(redisParam.getKeyGoodssku()), c -> {
            c.parallelStream().forEach(s -> {
                if (RedisParam.HASH.equals(jedisCluster.type(s))) {
                    boolean b = listToMap(s, (key, value) -> {
                        int parseInt = Integer.parseInt(value);
                        if (parseInt > 0) {
                            log.debug("sku  key {} value{} ", key, value);
                            HashMap<String, Object> map = new HashMap<String, Object>() {{
                                put("skuNo", key);
                                put("number", value);
                            }};
                            goodsSkuMapper.updateBatchBySkuNo(map);
                        } else {
                            log.error(" spu  id   {}  number is  {}", key, value);
                        }

                    });
                }
            });
        });
    }

//    /**
//     * 查询
//     *
//     * @param cursor
//     * @param match
//     * @param consumer
//     * @return
//     */
//    private String scan(String cursor, String match, Consumer<List<String>> consumer) {
//        ScanParams scanParams = new ScanParams();
//        scanParams.count(1000);
//        scanParams.match(match + "*");
//        ScanResult<String> scan = jedisCluster.scan(cursor, scanParams);
//        List<String> result = scan.getResult();
//        log.debug("scan  match  {}", result.size());
//        consumer.accept(result.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList()));
//        String stringCursor = scan.getStringCursor();
//        return stringCursor;
//    }

    public Optional<String> spiltGoodsNoByString(String redisKey) {
        String[] split = redisKey.split(":");
        if (split.length == 2) {
            return Optional.ofNullable(split[1]);
        }
        return Optional.empty();

    }

    /**
     * 将 list 的  redis  key  转成到相对应的   map<goodsNO,number></>
     *
     * @param c
     * @param hashMap
     */
    private void listToMap(List<String> c, HashMap<String, String> hashMap) {
        c.stream().forEach(key -> {
            Optional<String> s1 = spiltGoodsNoByString(key);
            s1.ifPresent(skuNo -> {
                String s = jedisCluster.get(key);
                if (StringUtils.isNotBlank(s)) {
                    hashMap.put(skuNo, s);
                }
            });
        });
    }

    private boolean listToMap(String redisKey, MyKey myKey) {
        Optional<String> s1 = spiltGoodsNoByString(redisKey);
        if (s1.isPresent()) {
            String s = jedisCluster.hget(redisKey, GoodsSpu.STOCK).get();
            if (StringUtils.isNotBlank(s)) {
                myKey.run(s1.get(), s);
            }
            return true;
        }

        return false;

    }


}

@FunctionalInterface
interface MyKey {
    void run(String key, String value);
}
