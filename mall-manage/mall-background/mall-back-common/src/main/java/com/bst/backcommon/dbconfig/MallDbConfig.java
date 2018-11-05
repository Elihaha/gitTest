package com.bst.backcommon.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 创建相应数据库的SqlSessionFactory <br/>
 * @author zhang
 * "com.bst.permission.mapper", "com.bst.verify.mapper"
 */
@Configuration
@MapperScan(basePackages = {"com.bst.common.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryMall")
public class MallDbConfig {

    @Autowired
    @Qualifier("mallDS")
    private DataSource mallDS;

    /**
     * 通过DataSourceConfig中的连接池创建SqlSessionFactory
     * @return SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryMall() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(mallDS);
        return factoryBean.getObject();
    }

    /**
     *   创建Spring-Mybatis模板对象
     * @return SqlSessionTemplate
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplateMall() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryMall());
    }

    /**
     * 创建事务管理器
     * @return PlatformTransactionManager
     */
    @Bean(name = "mallTransactionManager")
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(mallDS);
    }
}
