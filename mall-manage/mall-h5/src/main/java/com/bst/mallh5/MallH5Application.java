package com.bst.mallh5;

import com.pz.swagger.MySwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.bst.mallh5", "com.bst.common"})
@MapperScan(basePackages = {"com.bst.common.mapper"})
@MySwagger
public class MallH5Application {

    public static void main(String[] args) {
        SpringApplication.run(MallH5Application.class, args);
    }
}
