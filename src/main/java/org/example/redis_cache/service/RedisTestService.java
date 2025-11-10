package org.example.redis_cache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisTestService {
    private final StringRedisTemplate redisTemplate;

    public void saveData(String key, String value) {
        // TTL 1분 (60초) 설정
        redisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);
    }

    public String getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
}
