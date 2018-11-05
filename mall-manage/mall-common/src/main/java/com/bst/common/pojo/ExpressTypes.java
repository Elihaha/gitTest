package com.bst.common.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;

import java.io.Serializable;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/26 0:45 2018 09
 */
@Builder
public class ExpressTypes implements Serializable {



    private String name;
    private String code;


    public ExpressTypes() {
    }

    ExpressTypes(String name, String code) {
        this.name = name;
        this.code = code;
    }
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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
