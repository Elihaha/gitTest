package com.bst.goods.task;

import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.task.GoodsTask;
import com.bst.common.utils.RedisParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/26 14:55 2018 09
 */
@Component
@Slf4j
public class GoodsStockTask {

    @Autowired
    JedisCluster jedisCluster;


    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsSpuMapper goodsSpuMapper;

    /**
     * 同步商品spu库存到mysql
     */
    @Scheduled(fixedDelay = 3*60*1000)
    public void synchronizeGoodsSpuStock() {
        String cursor="-1";
        do {
            cursor = scan("0", RedisParam.KEY_GOODSSPU+ "*", c -> {
                HashMap<String, String> hashMap = new HashMap<>();
                listToMap(c, hashMap);
                hashMap.forEach((s, s2) -> {
                    if("SPUf4034d88-62be-4d39-b46d-3c891b5edb46".equals(s)){
                          log.info("spu  key {} value{} ",s,s2);
                    }
                    goodsSpuMapper.updateBatchBySpuNo(s, Integer.parseInt(s2));
                });

            });
        }while (!cursor.equals("0"));
    }




    /**
     * 同步商品Sku库存到mysql
     */
    @Scheduled(fixedDelay = 3*60*1000)
    public void synchronizeGoodsSkuStock() {
        String cursor="-1";
        do {
             cursor = scan("0", RedisParam.KEY_GOODSSKU+ "*" , c -> {
                 HashMap<String, String> hashMap = new HashMap<>();
                 listToMap(c, hashMap);
                 hashMap.forEach((s, s2) -> {
                     log.debug("sku  key {} value{} ",s,s2);
                     goodsSkuMapper.updateBatchBySkuNo(s, Integer.parseInt(s2));
                 });
             });
        }while (!cursor.equals("0"));
    }

    /**
     *    查询
     * @param cursor
     * @param match
     * @param consumer
     * @return
     */
    private String scan(String cursor, String match, Consumer<List<String>> consumer) {
        ScanParams scanParams = new ScanParams();
        scanParams.count(1000);
        scanParams.match(match+"*");
        ScanResult<String> scan = jedisCluster.scan(cursor, scanParams);
        List<String> result = scan.getResult();
        log.debug("scan  match  {}",result.size());
        consumer.accept(  result.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList()));
        String stringCursor = scan.getStringCursor();
        return stringCursor;
    }

    public Optional<String> spiltGoodsNoByString(String redisKey) {
        String[] split = redisKey.split(":");
        if (split.length == 2) {
         return    Optional.ofNullable(split[1]);
        }
        return Optional.empty();

    }

    /**
     *    将 list 的  redis  key  转成到相对应的   map<goodsNO,number></>
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



}
