package com.example.mongo_user.domain.services;

import com.example.mongo_user.domain.models.TokenInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Log4j2
@Service
public class CacheManager {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<String, TokenInfo> redisTokenTemplate;

    public static final Integer TIME_OUT = 12 * 60;

    public String getValue(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void setValue(String key, String value) {

        redisTemplate.opsForValue().set(
                key,
                value, Duration.ofMinutes(TIME_OUT)
        );
    }

    public void deleteValue(String key) {
        System.out.println(key);
        redisTemplate.delete(key);
    }


    public TokenInfo getTokenValue(String token) {
        try {
            return redisTokenTemplate.opsForValue().get(token);
        } catch (Exception e) {
            return null;
        }
    }

    public void setTokenValue(String token, String email, String password) {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserName(email);
        tokenInfo.setPassword(password);
        log.info(tokenInfo + "");
        redisTokenTemplate.opsForValue().set(
                token,
                tokenInfo, Duration.ofMinutes(TIME_OUT)
        );
    }

    public void deleteTokenValue(String token) {
        redisTemplate.delete(token);
    }
}
