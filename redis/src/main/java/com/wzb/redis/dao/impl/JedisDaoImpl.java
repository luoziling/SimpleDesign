package com.wzb.redis.dao.impl;

import com.wzb.redis.dao.JedisDao;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @author Satsuki
 * @time 2019/10/27 18:05
 * @description:
 */
@Repository
public class JedisDaoImpl implements JedisDao {
    @Resource
    private JedisCluster jedisCluster;

    @Override
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key,value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key,seconds);
    }
}
