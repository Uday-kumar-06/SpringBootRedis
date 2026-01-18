package com.api.redis.com.redis.example.dao;

import com.api.redis.com.redis.example.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final RedisTemplate<String, Object> redisTemplate;

    private static  final String KEY = "USER";

    public User save(User user){
        redisTemplate.opsForHash().put(KEY,user.getUserId(),user);
        return user;
    }

    public User get(String userId){
        return (User)redisTemplate.opsForHash().get(KEY,userId);
    }

    public Map<Object,Object> getAll(){
        return redisTemplate.opsForHash().entries(KEY);
    }

    public void delete(String userId){
        redisTemplate.opsForHash().delete(KEY,userId);
    }

}
