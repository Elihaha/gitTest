package com.bst.common.modle;


import com.bst.common.constants.HttpConstants;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Result implements Serializable, Cloneable {
    //状态码
    private Integer status;

    //失败消息
    private String msg;

    //结果对象
    private Object data;

    public Result() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @param
     * @desc: 成功返回
     * @return: Result
     */
    public static Result ok() {
        return Result.ok(HttpConstants.SUCCESS, "success", null);
    }

    public static Result ok(Object result) {
        return Result.ok(HttpConstants.SUCCESS, "success", result);
    }

    public static Result ok(Integer statusCode, Object result) {
        return Result.ok(statusCode, "success", result);
    }

    public static Result ok(String msg, Object result) {
        return Result.ok(HttpConstants.SUCCESS, msg, result);
    }

    public static Result ok(Integer statusCode, String message, Object result) {
        Result r = new Result();
        r.setStatus(statusCode);
        r.setMsg(message);
        r.setData(result);
        return r;
    }


    /**
     * @param message
     * @desc: 未知异常 状态码固定为500， 返回异常消息
     * @return:
     */
    public static Result error(String message) {
        return Result.error(HttpConstants.UNKNOW, message);
    }

    /**
     * @param
     * @desc: 已知异常, 返回该异常状态码及异常消息
     * @return:
     */
    public static Result error(Integer statusCode, String message) {
        Result r = new Result();
        r.setStatus(statusCode);
        r.setMsg(message);
        return r;
    }

    @JsonIgnore()
    private int isHash = 0;


    public Result put(String key, Object value) {
        if (1 == isHash) {
            ((HashMap) data).put(key, value);
            return  this;
        }
        if (Objects.isNull(this.data)) {
            castHashMap(key, value);
        } else if (!(data instanceof HashMap)) {
            Result result = this.clone();
            castHashMap(result,key, value);
        }
        return this;
    }

    private void castHashMap(String key, Object value) {
        final Map map = getMap();
        if (Objects.nonNull(key)) {
            map.put(key, value);
        }
        data = map;
        isHash = 1;
    }
    private void castHashMap(Result result,String key, Object value) {
        final Map map = getMap();
        final Object datac = result.getData();
        if (Objects.nonNull(datac)) {
            map.put(key, value);
            map.put("data",datac);
        }
        data = map;
        isHash = 1;
    }

    private Map getMap() {
        try {
            return HashMap.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    //使用clone()方法实现浅克隆
    public Result clone() {
        Object obj = null;
        try {
            obj = super.clone();
            return (Result) obj;
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制！");
            return null;
        }
    }


}