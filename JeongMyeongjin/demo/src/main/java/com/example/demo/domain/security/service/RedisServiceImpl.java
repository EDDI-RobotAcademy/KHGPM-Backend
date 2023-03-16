package com.example.demo.domain.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    final private StringRedisTemplate redisTemplate;

    @Override
    public void setKeyAndValue(String token, Long memberId) {
        String memberIdString = String.valueOf(memberId);
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        value.set(token, memberIdString, Duration.ofMinutes(60));
    }

    @Override
    public Long getValueByKey(String token) {
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        String tempMemberId = value.get(token);
        Long memberId;

        if (tempMemberId == null) { memberId = null; }
        else { memberId = Long.parseLong(tempMemberId); }

        return memberId;
    }

    @Override
    public void deleteByKey(String token) {
        redisTemplate.delete(token);
    }

    public boolean isRefreshTokenExists(String token) {
        return getValueByKey(token) != null;
    }
}