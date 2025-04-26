package com.archexc.acctmgmtservice.service;

import com.archexc.acctmgmtservice.model.UserDetail;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToCache(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getFromCache(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }


    public UserDetail getUserDetails(Long bankAccountId) {
        return UserDetail.builder()
                .name("John Doe")
                .email("john.doe@gmail.com")
                .build();
    }

    public UserDetail getUserDetailsByCustomerId(Long customerId) {
        return UserDetail.builder()
                .name("John Doe")
                .email("john.doe@gmail.com")
                .build();
    }
}
