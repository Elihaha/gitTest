package com.bst.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages={"com.bst"})
@MapperScan(basePackages = {"com.bst.common.mapper.*"})
@EnableScheduling
public class MallBackServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallBackServerApplication.class, args);
    }
}
