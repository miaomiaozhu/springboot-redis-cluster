package com.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName RedisConfig
 * @Author Jumy
 * @Description //TODO
 * @Date 2019/6/5 10:47
 * @Version 1.0
 **/
@Configuration
@ConditionalOnClass(JedisCluster.class)
public class RedisConfig {
    @Autowired
    private RedisProperties redisProperties;
    /**
     * 配置Redis 连接池信息
     **/
    @Bean
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        jedisPoolConfig.setTestOnBorrow(redisProperties.isTestOnBorrow());
        return jedisPoolConfig;
    }
    /**
    * 配置Redis CLuster信息
     **/
    @Bean
    public RedisClusterConfiguration getJedisCluster() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setMaxRedirects(redisProperties.getMaxRedirects());
        String[] nodes = redisProperties.getClusterNodes().split(",");
        //分割集群节点
        for (String node : nodes) {
            String[] host = node.split(":");
            RedisNode redis = new RedisNode(host[0], Integer.parseInt(host[1]));
            redisClusterConfiguration.addClusterNode(redis);
        }
        return redisClusterConfiguration;
    }
    /**
     * 配置Redis 连接工厂
     **/
    @Bean
    public JedisConnectionFactory getJedisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration, JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration,jedisPoolConfig);
        return redisConnectionFactory;
    }
    /**
     * 配置数据存入rdis的序列化方式
     **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        redisTemplate.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
