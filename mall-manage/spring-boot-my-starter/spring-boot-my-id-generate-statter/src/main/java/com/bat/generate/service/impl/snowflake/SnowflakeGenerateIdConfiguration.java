package com.bat.generate.service.impl.snowflake;

import com.bat.generate.GenerateIdProperties;
import com.bat.generate.service.GenerateIdFctory;

public class SnowflakeGenerateIdConfiguration implements GenerateIdFctory {



    private SnowflakeIdWorker snowflakeIdWorker;

    public SnowflakeGenerateIdConfiguration() {
        snowflakeIdWorker =new SnowflakeIdWorker();
    }

    public SnowflakeGenerateIdConfiguration(GenerateIdProperties generateIdProperties) {
       init( generateIdProperties);
    }

    private  void  init (GenerateIdProperties generateIdProperties){
         snowflakeIdWorker=new SnowflakeIdWorker(generateIdProperties.getWorkerId(),generateIdProperties.getDatacenterId());
    }

    @Override
    public String getGenerateId() {
        return snowflakeIdWorker.nextId()+"";
    }
}
