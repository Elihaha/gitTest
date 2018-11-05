package com.bst.mallh5.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author gan
 * @desc:  使用Swagger生成接口文档
 */
@Configuration
@EnableSwagger2
@ComponentScan("com.bst.mallh5")
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("openApi")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bst.mallh5"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("商城H5接口文档")
                .description(" 使用RESTful APIs")
                .termsOfServiceUrl("NO terms of service")
                .contact(new Contact("张治冶", "http://www.baidu.com", "zhangzhiye@bstyjy.com"))
                .version("1.0")
                .build();
    }


}
