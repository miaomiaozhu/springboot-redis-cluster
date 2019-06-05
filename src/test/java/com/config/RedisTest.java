package com.config;

import com.redis.domain.UserVo;
import com.redis.RedisHandle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    RedisHandle redisHandle;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    /*不封装*/
    @Test
    public void testValueOption( )throws  Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("上海");
        userVo.setName("jantent");
        userVo.setAge(23);
        redisTemplate. opsForValue().set("test",userVo);
        System.out.println( redisTemplate. opsForValue().get("test"));
    }
    /*封装RedisHandle*/
    @Test
    public void testValueOption_util( )throws  Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("上海");
        userVo.setName("jantent");
        userVo.setAge(23);
        redisHandle.set("user",userVo);
        System.out.println(redisHandle.get("test"));
    }
}