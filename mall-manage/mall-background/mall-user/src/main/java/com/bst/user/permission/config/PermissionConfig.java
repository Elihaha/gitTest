package com.bst.user.permission.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 创建相应数据库的SqlSessionFactory <br/>
 * @author zhang
 * "com.bst.permission.mapper", "com.bst.verify.mapper"
 */
@Configuration
@MapperScan(basePackages = {"com.bst.user.permission.mvc.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryPermission")
public class PermissionConfig {

    @Autowired
    @Qualifier("permissionDS")
    private DataSource permissionDS;

    @Bean
    public SqlSessionFactory sqlSessionFactoryPermission() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(permissionDS);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplatePermission() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryPermission());
        return template;
    }
}
