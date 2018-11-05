package com.bat.generate;

import com.bat.generate.service.GenerateIdFctory;
import com.bat.generate.service.impl.snowflake.SnowflakeGenerateIdConfiguration;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Configurable
@Import(MyGenerateIdAutoConfiguration.class)
public class DefaultGenerateIdConfiguration {


    @Bean
    @ConditionalOnMissingBean(GenerateIdFctory.class)
    public GenerateIdFctory generateIdFctory(){
        return new SnowflakeGenerateIdConfiguration();
    }


}
