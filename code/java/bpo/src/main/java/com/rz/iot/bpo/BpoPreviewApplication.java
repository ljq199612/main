package com.rz.iot.bpo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@MapperScan(value = {"com.rz.iot.bpo.mapper", "com.rz.iot.bpo.mina.mapper"})
@EnableTransactionManagement
@EnableCaching
public class BpoPreviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(BpoPreviewApplication.class, args);
    }

}
