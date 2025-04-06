package com.admin.client.redisson.service.impl;

import com.admin.client.redisson.service.RedissonClientService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedissonClientServiceImpl implements RedissonClientService {
    private final RedissonClient redissonClient;

    @Override
    public Object get(String key) {
        return redissonClient.getBucket(key).get();
    }

    @Override
    public void put(String key, Object value, Long expirationTime) {
        redissonClient.getBucket(key).set(value, expirationTime, TimeUnit.MILLISECONDS);
    }
}
