package com.bst.common.config.Snowflake;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/27 16:22 2018 09
 */
public enum  IdGenerate {

    /**
     * SPU编号前准
     */
     SPU_NO_PREFIX ("SPU"),

    /**
     * SKU编号前缀
     */
     SKU_NO_PREFIX ("SKU"),

    /**
     * 规格编号前缀
     */
    SPEC_NO_PREFIX ("SPEC"),

    /**
     * 订单编号前缀
     */
     ORDER_NO_PREFIX ( "ORD"),
    /**
     * 子订单编号前缀
     */
    SCHILD_ORDER_NO("CORD"),
    /**
     *  物流编号前缀
     */
     LOCALDATETIME_NO_PREFIX ( "LOC");

    private String name;

    IdGenerate(String name) {
        this.name = name;
    }

    public static String generate(IdGenerate param){
        for (IdGenerate value : IdGenerate.values()) {
            if(value==param){
                 return  value.name+SnowflakeId.getId();
            }
        }
        throw  new RuntimeException("没有找到相关前缀");
    }
}
