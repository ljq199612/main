package com.xxx.module.soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceStarter {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceStarter.class, args);
    }
}

// NOTICE
// 1. 这里做的是一个简单的用户和订单的服务
// 2. 用户服务是服务的提供者, 订单服务是服务的消费者
// 3. 先启动 UserServiceStart, 测试 localhost:8083/user/test/ljq
// 4. 再启动 OrderServiceStart 测试 localhost:8084/order/test/2/1234567
// 5. 一个简单的微服务跑起来了