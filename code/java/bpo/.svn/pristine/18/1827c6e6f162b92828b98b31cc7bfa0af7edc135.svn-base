package com.rz.iot.bpo.framework.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述 : 配置RedisCache
 * 作者 : xuxiake
 * 创建时间 : 2020/6/19 12:01
 * <p>
 * 修改原因 :
 * 修改人 : xuxiake
 * 修改时间 ：2020/6/19 12:01
 */
@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory);

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        // 缓存永不过期
        cacheConfigurations.put("province", this.redisCacheConfiguration(Duration.ZERO));
        cacheConfigurations.put("city", this.redisCacheConfiguration(Duration.ZERO));
        cacheConfigurations.put("district", this.redisCacheConfiguration(Duration.ZERO));
        RedisCacheManager redisCacheManager = redisCacheManagerBuilder.withInitialCacheConfigurations(cacheConfigurations).build();
        return redisCacheManager;
    }

    private RedisCacheConfiguration redisCacheConfiguration(Duration duration){
        GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(genericFastJsonRedisSerializer)).entryTtl(duration);
        return configuration;
    }
}
