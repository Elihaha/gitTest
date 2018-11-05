package com.bst.mallh5.user;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bst.common.exception.MallAuthorizationException;
import com.bst.mallh5.shiro.PlatformResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.Map;

/**
 * 当前用户工具类
 */
@Service
public class UserService {

    @Value("${platform.user.interface}")
    private String platformUrl;

    @Value("${platform.connect.timeout}")
    private int connectTimeout;

    @Value("${platform.user.timeout}")
    private int platformUserTimeOut;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*static String USER_PREFIX = "aqjy_ticket_";

    private final JedisCluster jedisCluster;

    @Autowired
    public UserService(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }*/

    /**
     * 判断用户是否存在
     * @param ticket 票据
     * @return
     */
   /* public boolean isUserExists(String ticket) {
        boolean exists = jedisCluster.exists(USER_PREFIX + ticket);
        if (exists) {
            return true;
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("ticket", ticket);
        String result = HttpUtil.get(platformUrl, params, connectTimeout);
        if (result == null) {
            return false;
        }
        PlatformResult platformResult = JSONObject.parseObject(result, PlatformResult.class);
        if (platformResult.getErr_code() != 0) {
            throw new MallAuthorizationException(platformResult.getErr_desc());
        }
        PlatformUser platformUser = platformResult.getData();
        if (platformUser != null) {
            jedisCluster.set(USER_PREFIX + ticket, JSON.toJSONString(platformUser));
            jedisCluster.expire(USER_PREFIX + ticket, platformUserTimeOut);
        } else {
            return false;
        }
        return true;
    }*/

    /**
     * 获取平台用户信息
     *
     * @param ticket 票据
     * @return 平台用户信息对象
     */
    public PlatformUser getPlatformUser(String ticket) {
        /*String info = jedisCluster.get(USER_PREFIX + ticket);
        if (info != null) {
            return JSON.parseObject(info, PlatformUser.class);
        }*/
        Map<String, Object> params = new HashMap<>(1);
        params.put("ticket", ticket);
        String result = HttpUtil.get(platformUrl, params, connectTimeout);
        if (result == null) {
            throw new MallAuthorizationException("认证失败！");
        }
        PlatformResult platformResult;
        try {
            platformResult = JSONObject.parseObject(result, PlatformResult.class);
        } catch (Exception ex) {
            logger.error("get platform user info error.result:{}", result, ex);
            throw new MallAuthorizationException("解析用户信息失败!");
        }
        if (platformResult.getErr_code() != 0) {
            throw new MallAuthorizationException(platformResult.getErr_desc());
        }
        /* if (platformUser != null) {
            jedisCluster.set(USER_PREFIX + ticket, JSON.toJSONString(platformUser));
            jedisCluster.expire(USER_PREFIX + ticket, platformUserTimeOut);
        } else {
            throw new MallAuthorizationException("认证失败！");
        }*/
        return platformResult.getData();
    }
}
