package com.example.mongo_user.domain.utils;

import com.example.mongo_user.domain.entities.LuckySpinGift;
import com.example.mongo_user.domain.models.TokenInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Log4j2
@Service
public class CacheManager {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<String, TokenInfo> redisTokenTemplate;

    @Autowired
    RedisTemplate<String, String> template;

    public static final Integer TIME_OUT = 1;

    public void set(String key, String value) {
        template.opsForValue().set(key, value,Duration.ofMinutes(TIME_OUT));
    }

    public List<LuckySpinGift> getGift() {
        try {
            String key = CacheKey.testCache();
            return JsonParser.arrayList(get(key), LuckySpinGift.class);
        } catch (Exception e) {
            return null;
        }
    }

    public String get(String key) {
        return template.opsForValue().get(key);
    }

    public <T> T get(String key, Class<T> tClass) throws Exception {
        String value = template.opsForValue().get(key);
        return JsonParser.entity(value, tClass);
    }

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
