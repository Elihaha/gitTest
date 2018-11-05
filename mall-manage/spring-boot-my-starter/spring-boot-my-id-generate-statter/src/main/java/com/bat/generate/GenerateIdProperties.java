package com.bat.generate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "generate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateIdProperties {

    //  分布式 生成id 的序号
    /**
     *  workerId 工作ID (0~31)
     */
     private  long workerId;
    /**
     *   datacenterId 数据中心ID (0~31)
     */
    private  long datacenterId;



}
