package com.bst.common.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/26 0:45 2018 09
 */
public enum ExpressType {
    YUANTONG("圆通", "yuantong"),
    SHUNFENG("顺丰", "shunfeng"),
    ZHONGTONG("中通", "zhongtong"),

    ;


    private String name;
    private String code;

    ExpressType(String name, String code) {
        this.name = name;
        this.code = code;
    }
    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonValue
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public static String getNameByCode(String code){
        for (ExpressType value : ExpressType.values()) {
            if (value.code.equals(code)) {
                return   value.name;
            }
        }
        return  "中国快递";
    }
}
