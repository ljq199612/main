package com.rz.iot.bpo.framework.config;

import com.rz.iot.bpo.common.utils.ServletUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 描述 : bean 配置
 * 作者 : xuxiake
 * 创建时间 : 2020/6/26 14:07
 * <p>
 * 修改原因 :
 * 修改人 : xuxiake
 * 修改时间 ：2020/6/26 14:07
 */
@Configuration
public class BeanConfig {

    /**
     * 跨域配置
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:9527", "*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "OPTIONS", "DELETE"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("X-Token", "Origin", "No-Cache", "X-Requested-With", "If-Modified-Since", "Pragma", "Last-Modified", "Cache-Control", "Expires", "Content-Type", "X-E4M-With", "userId", "token", "Access-Control-Allow-Headers"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
