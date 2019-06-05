package com.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisProperties
 * @Author Jumy
 * @Description //TODO
 * @Date 2019/6/5 14:19
 * @Version 1.0
 **/
@Component
@PropertySource(value="classpath:redis.properties")
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    //超时时间
    private int expireSeconds;
    //集群节点
    private String clusterNodes;
    //密码
    private String password;
    //最大活跃链接
    private int maxActive;
    //连接池最大阻塞等待时间（负值表示没有限制）
    private int maxWait;
    //连接池中的最大空闲连接
    private int maxIdle;
    //连接池中的最小空闲连接
    private int minIdle;
    //连接/执行命令超时时间（毫秒）
    private int commandTimeout;
    //token生效时间
    private long tokenTime;
    //重试次数
    private int maxAttempt;
    //跨集群执行命令时要遵循的最大重定向数量
    private int maxRedirects;
    //是否在池中取出连接前进行检验，如果检验失败，则从池中去除连接并尝试取出另一个
    private boolean testOnBorrow;

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    public long getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(long tokenTime) {
        this.tokenTime = tokenTime;
    }

    public int getMaxAttempt() {
        return maxAttempt;
    }

    public void setMaxAttempt(int maxAttempt) {
        this.maxAttempt = maxAttempt;
    }

    public int getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(int maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
}
