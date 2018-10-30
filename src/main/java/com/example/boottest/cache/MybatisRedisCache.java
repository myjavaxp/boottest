package com.example.boottest.cache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisRedisCache implements Cache {
    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisRedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private String id;
    private static RedisTemplate<String, Object> redisTemplate;
    private static ValueOperations<String, Object> valueOperations;

    public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        MybatisRedisCache.redisTemplate = redisTemplate;
    }

    public static void setValueOperations(ValueOperations<String, Object> valueOperations) {
        MybatisRedisCache.valueOperations = valueOperations;
    }

    public MybatisRedisCache(final String id) {
        Assert.notNull(id, "Cache instances require an ID");
        LOGGER.info("Redis Cache id: " + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (null != value) {
            valueOperations.set(key.toString(), value, 2, TimeUnit.DAYS);
        }
    }

    @Override
    public Object getObject(Object key) {
        if (null != key) {
            return valueOperations.get(key.toString());
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        if (null != key) {
            Assert.notEmpty(redisTemplate.keys(key.toString()), key.toString() + ":key值不存在");
            redisTemplate.delete(key.toString());
        }
        return null;
    }

    @Override
    public void clear() {
        redisTemplate.execute((RedisCallback<Long>) c -> {
            c.flushDb();
            c.flushAll();
            c.close();
            LOGGER.info("Redis缓存已清空");
            return 1L;
        });
    }

    @Override
    public int getSize() {
        return Objects.requireNonNull(redisTemplate.execute(RedisServerCommands::dbSize)).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}