package com.example.boottest.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class IRedisService<T> {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private HashOperations<String, String, T> hashOperations;

    /**
     * 存入redis中的key
     *
     */
    protected abstract String getRedisKey();

    /**
     * 添加
     *
     * @param key    key
     * @param domain 对象
     * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
     */
    public void put(String key, T domain, long expire) {
        hashOperations.put(getRedisKey(), key, domain);
        if (expire != -1) {
            redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 删除
     *
     * @param key 传入key的名称
     */
    public void remove(String key) {
        hashOperations.delete(getRedisKey(), key);
    }

    /**
     * 查询
     *
     * @param key 查询的key
     */
    public T get(String key) {
        return hashOperations.get(getRedisKey(), key);
    }

    /**
     * 获取当前redis库下所有对象
     *
     */
    public List<T> getAll() {
        return hashOperations.values(getRedisKey());
    }

    /**
     * 查询查询当前redis库下所有key
     *
     */
    public Set<String> getKeys() {
        return hashOperations.keys(getRedisKey());
    }

    /**
     * 判断key是否存在redis中
     *
     * @param key 传入key的名称
     */
    public boolean isKeyExists(String key) {
        return hashOperations.hasKey(getRedisKey(), key);
    }

    /**
     * 查询当前key下缓存数量
     *
     */
    public long count() {
        return hashOperations.size(getRedisKey());
    }

    /**
     * 清空redis
     */
    public void empty() {
        Set<String> set = hashOperations.keys(getRedisKey());
        set.forEach(key -> hashOperations.delete(getRedisKey(), key));
    }
}