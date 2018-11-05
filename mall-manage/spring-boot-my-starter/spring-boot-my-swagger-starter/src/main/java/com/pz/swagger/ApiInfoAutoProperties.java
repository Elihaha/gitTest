package com.pz.swagger;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/2 10:49
 * @email 226804871@qq.com
 */
@ConfigurationProperties(prefix = "swagger")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiInfoAutoProperties {

    private String title = " 前后端接口-外部";
    private String description = "外部接口文档";
    private String version = "1.0.0";

    private String userName = "pz";
    private String userUrl = "www.baidu.com";
    private String userEmail = "2919274153@qq.com";
    private String licence = "Apache 2.0"; // licence
    private String licenceUrl = "http://www.apache.org/licenses/LICENSE-2.0.html"; // licence url
    private String serviceUrl = "http://www.apache.org/licenses/LICENSE-2.0.html"; // licence url


}
