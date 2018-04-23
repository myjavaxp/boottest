package com.example.boottest.config;

import com.example.boottest.cache.MybatisRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Configuration
public class RedisCacheTransfer {
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        MybatisRedisCache.setRedisTemplate(redisTemplate);
    }

    @Autowired
    public void setValueOperations(ValueOperations<String, Object> valueOperations) {
        MybatisRedisCache.setValueOperations(valueOperations);
    }
}