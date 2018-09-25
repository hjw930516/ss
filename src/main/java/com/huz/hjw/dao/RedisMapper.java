package com.huz.hjw.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisMapper {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setVal(String key,String val){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key,val,1000);
    }

    public String getVal(String key){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }
}
