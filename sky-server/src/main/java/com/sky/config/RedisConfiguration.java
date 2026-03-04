package com.sky.config;

import io.lettuce.core.models.role.RedisSentinelInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * ClassName: RedisConfiguration
 * Package: com.sky.config
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/3/4 16:34
 * @Version 1.0
 */
@Configuration
@Slf4j

public class RedisConfiguration
{
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisTemplateFactory)
    {    log.info("开始创建RedisTemplate对象...");
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisTemplateFactory);
        //设置key的序列化器
        //redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
