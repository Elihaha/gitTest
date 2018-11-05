package com.bst.common.utils;


/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/20 15:33 2018 09
 * redis 的参数
 */

public class RedisParam {

    /**
     *   mallGoodsSpuBaseInfo : goods_spu_baseInfo_key
     *     orderlogistics: ORDERLOGISTICS
     *     keyGoodssku: GOODSSKU
     *     keyGoodsspu: GOODSSPU
     *     pushMysql: PUSHMYSQL
     *     courierCompanyCode: COURIER_COMPANY_CODE
     *     courierCompanyName: COURIER_COMPANY_NAME
     *     postageConfig: POSTAGE_CONFIG
     */
    public  final  static  String ORDERLOGISTICS="{ORDERLOGISTICS}:";
    /**
     *   sku 前綴
     */
    public static final String KEY_GOODSSKU = "{GOODSSKU}:";
    /**
     *   spu 前綴
     */
    public static final String KEY_GOODSSPU = "{GOODSSPU}:";

    /**
     *   更新数据 的消息队列的 名字
     */
    public static final String PUSH_MYSQL= "PUSHMYSQL";


     /////////////////////////////   redis  spu   hash  field

    /**
     *   field total_stock表示剩余库存，
     */
    public static final String TOTAL_STOCK= "TOTAL_STOCK";
    /**
     *   field total_putaway总共上架的商品总数，
     */
    public static final String TOTAL_PUTAWAY= "TOTAL_PUTAWAY";
    /**
     *   field soldout_count售出总数
     */
    public static final String SOLDOUT_COUNT= "SOLDOUT_COUNT";



    /////////////////////////////   redis  sku   hash  field

    /**
     *   field 表示剩余总库存，
     */
    public static final String STOCK= "STOCK";
    /**
     *   field soldout_count售出总数
     */
//    public static final String SOLDOUT_COUNT= "soldout_count";
    /**
     *   field soldout_count售出总数
     */
    public static final String COURIER_COMPANY= "{COURIER_COMPANY}:";

    /*redisParam.getOrderTimeoutDelayKey()*/

    /*redisParam.getGoodsShopBaseInfoKey()*/

    /*redisParam.getGoodsSpuBaseInfoKey()*/

}
