package com.bst.user.permission.shiro;

import com.bst.user.permission.mvc.CustomSessionManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisClusterManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import redis.clients.jedis.JedisPoolConfig;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author gan
 * @desc:  Shiro配置
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String host;
    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisClusterManager redisManager() {
        RedisClusterManager redisClusterManager = new RedisClusterManager();
        redisClusterManager.setJedisPoolConfig(jedisPoolConfig);
        redisClusterManager.setHost(host);
        final String password = redisProperties.getPassword();
        if(!StringUtils.isBlank(password)){
            redisClusterManager.setPassword(password);
        }
        redisClusterManager.setTimeout((int) redisProperties.getTimeout().toMillis());
        return redisClusterManager;
    }

    /**
     * @desc: Shiro拦截器配置
     * @param securityManager
     * @return:
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/swagger-ui.*", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/configuration/**", "anon");
        filterMap.put("/external/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/projects/cache_clear", "anon");
        filterMap.put("/safeedu/infos/interfaces/**", "anon");
        filterMap.put("/phoenixcache/clear", "anon");
        filterMap.put("/art_model/**", "anon");
        filterMap.put("/orderlogistics/queryInfo/**", "anon");

        filterMap.put("/**", "authc");
        shiroFilter.setLoginUrl("/unauth");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     * @desc: 安全管理器
     * @param userRealm
     * @param sessionManager
     * @return:
     */
    @Bean("securityManager")
    public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }


    /**
     * @desc: 用户会话管理
     * @param
     * @return:
     */
    @Bean("sessionManager")
    public SessionManager sessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();
        customSessionManager.setSessionDAO(redisSessionDAO());
        return customSessionManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * @desc: 开启Shiro aop注解支持 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return:
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
