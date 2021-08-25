package com.al.o2o.config.redis;

import com.al.o2o.cache.JedisPoolWriper;
import com.al.o2o.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import sun.dc.pr.PRError;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.config.redis
 * @ClassName:RedisConfiguration
 * @Description redis配置
 * @date2021/8/13 17:27
 */
@Configuration
public class RedisConfiguration {
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.maxIdle}")
    private int maxIdle;
    @Value("${redis.maxWaitMillis}")
    private long maxWaitMillis;
    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${redis.hostname}")
    private String hostname;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.password}")
    private String password;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;
    @Autowired
    private JedisPoolWriper jedisPoolWriper;
    @Autowired
    private JedisUtil jedisUtil;

    /**
     * Redis连接池设置
     * @return
     */
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig createJedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    /**
     * 创建Redis连接池，并做相关设置
     * @return
     */
    @Bean(name = "jedisWritePool")
    public JedisPoolWriper createJedisPoolWriper(){
        JedisPoolWriper jedisPoolWriper = new JedisPoolWriper(jedisPoolConfig,hostname,port,timeout,password);
        return jedisPoolWriper;
    }

    /**
     * 创建Redis工具类，封装好Redis的连接以进行相关操作
     * @return
     */
    @Bean(name = "jedisUtil")
    public JedisUtil createJedisUtil(){
        JedisUtil jedisUtil = new JedisUtil();
        jedisUtil.setJedisPool(jedisPoolWriper);
        return jedisUtil;
    }

    /**
     * Redis的Key操作
     * @return
     */
    @Bean(name = "jedisKeys")
    public JedisUtil.Keys createkeys(){
        JedisUtil.Keys jedisKeys = jedisUtil.new Keys();
        return jedisKeys;
    }

    /**
     * Redis对String的操作
     * @return
     */
    @Bean(name = "jedisStrings")
    public JedisUtil.Strings createStrings(){
        JedisUtil.Strings jedisStrings = jedisUtil.new Strings();
        return jedisStrings;
    }

    /**
     * Redis的List操作
     * @return
     */
    @Bean(name = "jedisLists")
    public JedisUtil.Lists createLists(){
        JedisUtil.Lists jedisLists = jedisUtil.new Lists();
        return jedisLists;
    }

    /**
     * Redis的Set操作
     * @return
     */
    @Bean(name = "jedisSets")
    public JedisUtil.Sets createSets(){
        JedisUtil.Sets jedisSets = jedisUtil.new Sets();
        return jedisSets;
    }

    /**
     * Redis的Hash操作
     * @return
     */
    @Bean(name = "jedisHash")
    public JedisUtil.Hash createHash(){
        JedisUtil.Hash jedisHash = jedisUtil.new Hash();
        return jedisHash;
    }
}
