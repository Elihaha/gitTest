package com.bst.scheduled;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages={"com.bst"})
@MapperScan(basePackages = {"com.bst.common.mapper.*"})
@EnableScheduling
public class MallScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallScheduledApplication.class, args);
    }
}
