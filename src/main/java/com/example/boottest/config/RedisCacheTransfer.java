package com.example.boottest.config;

import com.example.boottest.cache.MybatisRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisCacheTransfer {
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        MybatisRedisCache.setRedisTemplate(redisTemplate);
        MybatisRedisCache.setValueOperations(redisTemplate.opsForValue());
    }
}