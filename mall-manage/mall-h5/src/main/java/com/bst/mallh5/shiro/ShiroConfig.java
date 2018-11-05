package com.bst.mallh5.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author gan
 * @desc: Shiro配置
 */
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Value("${spring.redis.cluster.nodes}")
    private String host;

    /**
     * @param securityManager
     * @desc: Shiro拦截器配置
     * @return:
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = DefaultFilter.createInstanceMap(null);
        filterMap.put("statelessAuthc", (Filter) ClassUtils.newInstance(StatelessAuthcFilter.class));
        shiroFilter.setFilters(filterMap);
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/swagger-ui.*", "anon");
        filterChainMap.put("/webjars/**", "anon");
        filterChainMap.put("/swagger-resources/**", "anon");
        filterChainMap.put("/configuration/**", "anon");
        filterChainMap.put("/v2/**", "anon");
        filterChainMap.put("/mallh5/goods/**", "anon");
        filterChainMap.put("/orderlogistics/queryInfo/**", "anon");
        filterChainMap.put("/wxpay/**", "anon");
        filterChainMap.put("/**", "statelessAuthc");
        shiroFilter.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilter;
    }

    /**
     * Shiro默认会使用Servlet容器的Session,可通过sessionMode属性来指定使用Shiro原生Session
     *
     * @param userRealm
     * @desc: 安全管理器
     * @return:
     */
    @Bean("securityManager")
    public SecurityManager securityManager(StatelessRealm userRealm, StatelessDefaultSubjectFactory subjectFactory, DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        SessionStorageEvaluator storageEvaluator = subjectDAO.getSessionStorageEvaluator();
        ((DefaultSessionStorageEvaluator) storageEvaluator).setSessionStorageEnabled(false);
        securityManager.setSubjectFactory(subjectFactory);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * 定义缓存管理器
     *
     * @return 缓存管理器
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
        webSessionManager.setSessionValidationSchedulerEnabled(false);
        return webSessionManager;
    }

    /**
     * 相当于调用SecurityUtils.setSecurityManager(securityManager)
     *
     * @param securityManager 安全管理器
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager) {
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(securityManager);
        return factoryBean;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * AOP式方法级权限检查
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * @param securityManager
     * @desc: 开启Shiro aop注解支持 使用代理方式;所以需要开启代码支持;
     * @return:
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
