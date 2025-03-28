/*
package com.example.hassan.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Save token with an expiration time
    public void saveToken(String tokenKey, Object tokenValue, long expirationSeconds) {
        redisTemplate.opsForValue().set(tokenKey, tokenValue, Duration.ofSeconds(expirationSeconds));
    }

    // Retrieve token
    public Object getToken(String tokenKey) {
        return redisTemplate.opsForValue().get(tokenKey);
    }

    // Delete token
    public void deleteToken(String tokenKey) {
        redisTemplate.delete(tokenKey);
    }
}
*/
