package com.bst.user.permission.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author gan
 * @desc:  多数据源配置，主要通过创建多个SessionFactory扫描不同的DB配置文件
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "permissionDS")
    @ConfigurationProperties(prefix = "spring.datasource.permission")
    public DataSource permissionDataSource() {
        return DataSourceBuilder.create().build();
    }


}
