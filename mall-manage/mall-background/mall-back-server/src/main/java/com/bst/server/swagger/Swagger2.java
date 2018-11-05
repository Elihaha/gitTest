package com.bst.server.swagger;

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
@ComponentScan("com.bst")
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("innerApi")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bst"))
                .paths(PathSelectors.any())
                .build();
    }

    //@Bean
    public Docket openApi() {
        /*Predicate<RequestHandler> predicate = input -> {
            //只有添加了ApiOperation注解的method才在API中显示
            return input.isAnnotatedWith(ApiOperation.class);
        };*/
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("openApi")
                .apiInfo(openApiInfo())
               /* .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)*/
                .select()
                //.apis(predicate::test)
                .apis(RequestHandlerSelectors.basePackage("com.bst.external"))
                .paths(PathSelectors.any())//过滤的接口
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("商城管理系统接口文档")
                .description(" 使用RESTful APIs")
                .termsOfServiceUrl("NO terms of service")
                .contact(new Contact("张治冶", "http://www.baidu.com", "zhangzhiye@bstyjy.com"))
                .version("1.0")
                .build();
    }

    private ApiInfo openApiInfo() {
        return new ApiInfoBuilder()
                .title("益问答系统对外接口")//大标题
                .description("益问答创建问题、回答、评论 ~ 对外的api文档")//详细描述
                .termsOfServiceUrl("NO terms of service")
                .contact(new Contact("张治冶","http://www.baidu.com", "zhangzhiye@bstyjy.com"))//作者
                .version("1.0")//版本
                .build();
    }

}
