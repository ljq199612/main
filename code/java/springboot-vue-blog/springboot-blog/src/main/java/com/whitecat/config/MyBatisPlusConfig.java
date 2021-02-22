package com.whitecat.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 刘哲贤
 * @since 2020-07-26
 * MyBatisPlus分页插件
 */
@Configuration
@EnableTransactionManagement
@ConditionalOnClass(value = {PaginationInterceptor.class})
@MapperScan("com.whitecat.blog.mapper*")
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
