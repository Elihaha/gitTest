package com.bst.common.utils;

import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/20 15:33 2018 09
 */
@ConfigurationProperties(prefix = "redis.config")
@Component
@Data
public class RedisParam {

    private String orderlogistics;
    /**
     * sku 前綴
     */
    private String keyGoodssku;
    /**
     * skuAll 前綴
     */
    private String keyGoodsskuAll;
    /**
     * spu 前綴
     */
    private String keyGoodsspu;

    /**
     * 更新数据 的消息队列的 名字
     */
    private String pushMysql;

    /**
     * field 快递公司
     */
    private String courierCompanyCode;
    /**
     * field 快递公司
     */
    private String courierCompanyName;
    /**
     * field   运费
     */
    private String postageConfig;

    /**
     * field 商品key
     */
    private String goodsSpuBaseInfoKey;


    /**
     * field  商店首页key
     */
    private String goodsShopBaseInfoKey;




    /**
     * field 订单有效期延时
     */
    private String orderTimeoutDelayKey;


    /**
     *    自动签收
     * @return
     */
    private String orderAutomaticReceipt;

    /**
     *   关闭退货
     */
    private String orderCloseTheReturn;

    /**
     * orderRefundReturnTimeout   退款退货超时
     * @return
     */
    private String orderRefundReturnTimeout;

    /**
     *   退款超时
     * @return
     */
  private String orderRefundTimeout;

    /**
     *   拒绝退货超时
     * @return
     */
  private String orderRefuseReturnTimeout;


    public String getOrderlogistics() {
        return hashKey(orderlogistics);
    }

    public String getKeyGoodssku() {
        return hashKey(keyGoodssku);
    }

    public String getKeyGoodsspu() {
        return hashKey(keyGoodsspu);
    }

    public String getPushMysql() {
        return hashKey(pushMysql);
    }

    public String getGoodsSpuBaseInfoKey() {
        return replaceAll(goodsSpuBaseInfoKey);
    }
    public String getGoodsShopBaseInfoKey() {
        return replaceAll(goodsShopBaseInfoKey);
    }

    public String getOrderCloseTheReturn() {
        return hashKey(orderCloseTheReturn);
    }

    public String getOrderRefundReturnTimeout() {
        return hashKey(orderRefundReturnTimeout);
    }

    public String getOrderRefundTimeout() {
        return hashKey(orderRefundTimeout);
    }

    public String getOrderRefuseReturnTimeout() {
        return hashKey(orderRefuseReturnTimeout);
    }

    private String replaceAll(String goodsSpuBaseInfoKey) {
        return hashKey(goodsSpuBaseInfoKey.replaceAll("_",":"),COLON);
    }

    public String getOrderTimeoutDelayKey() {
        return hashKey(orderTimeoutDelayKey);
    }

    public String getCourierCompanyCode() {
        return hashKey(courierCompanyCode, COLON);
    }

    public String getCourierCompanyName() {
        return hashKey(courierCompanyName, COLON);
    }

    public String getPostageConfig() {
        return hashKey(postageConfig, COLON);
    }

    public String getKeyGoodsskuAll() {
        return hashKey(keyGoodsskuAll,COLON);
    }

    public String getOrderAutomaticReceipt() {
        return hashKey(orderAutomaticReceipt);
    }

    //    public static String getKeyGoodsskuAll(String goodsShopBaseInfoKey, @NonNull String type) {
//        return hashKey(keyGoodsskuAll,type,COLON);
//    }
//    public  String getKeyGoodsskuAll(@NonNull String type) {
//        return hashKey(keyGoodsskuAll,type,COLON);
//    }

    // hash  處理
    public static String hashKey(String key) {
        return hashKey(key, "");
    }

    public static String hashKey(String key, @NonNull String... colon) {
        String  keys= PREFIX + key;
        for (String s : colon) {
           if(StringUtils.isNotBlank(s)){
               keys +=  s;
           }
        }
          keys=keys+ SUFFIX;
        return keys;
    }



    private static final String PREFIX = "{";
    private static final String SUFFIX = "}";
    public  static final String COLON = ":";
    public  static final String HASH = "HASH";
    public  static final String SET = "SET";

}
