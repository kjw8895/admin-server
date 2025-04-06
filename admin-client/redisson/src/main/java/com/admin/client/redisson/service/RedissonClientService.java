package com.admin.client.redisson.service;

public interface RedissonClientService {
    Object get(String key);
    void put(String key, Object value, Long expirationTime);
}
