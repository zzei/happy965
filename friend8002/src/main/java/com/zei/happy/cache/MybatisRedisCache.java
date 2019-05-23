package com.zei.happy.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用redis作为mybatis作为二级缓存
 */
public class MybatisRedisCache implements Cache{

    //过期时间 单位 天
    private final Integer TTL = 1;

    private final ReadWriteLock READWRITELOCK = new ReentrantReadWriteLock(true);

    private RedisTemplate<String, Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");

    private String id;

    public MybatisRedisCache(String id){
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if(value != null){
            //过期时间1天
            redisTemplate.opsForValue().set(key.toString(),value,TTL, TimeUnit.DAYS);
        }
    }

    @Override
    public Object getObject(Object key) {
        if(key != null){
            try {
                Object obj = redisTemplate.opsForValue().get(key.toString());
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        if(key != null){
            try {
                redisTemplate.delete(key.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void clear() {
        try {
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if(!CollectionUtils.isEmpty(keys)){
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        Long size = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.dbSize();
            }
        });
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.READWRITELOCK;
    }
}