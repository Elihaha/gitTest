package com.bat.generate;

import com.bat.generate.service.GenerateIdFctory;
import com.bat.generate.service.impl.snowflake.SnowflakeGenerateIdConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@Configurable
@EnableConfigurationProperties({GenerateIdProperties.class})
public class MyGenerateIdAutoConfiguration {


    @Autowired
    private GenerateIdProperties generateIdProperties;


    @Bean
    @ConditionalOnBean(GenerateIdFctory.class)
    private GenerateIdFctory generateIdFctory(){
           return  new SnowflakeGenerateIdConfiguration(generateIdProperties);
    }


}
