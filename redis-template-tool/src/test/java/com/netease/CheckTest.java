package com.netease;


import com.netease.lib.redistemplatetool.util.RedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;

//@SpringBootTest(classes = RedisSpringEnvironmentConfiguration.class)
//@RunWith(SpringRunner.class)
public class CheckTest {
    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private RedisTool redisTool;
    @Nullable
    private DataSource dataSource;

//    @Test
    public void queryDataTest() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String redisKey = operations.get("redis-key");
        System.out.println("redisKey：" + redisKey);
        operations.set("redis-key", "hello world");
        redisKey = operations.get("redis-key");
        System.out.println("redisKey：" + redisKey);
        // 对redis的key的value进行加1
        redisTemplate.opsForValue().increment("increment-key");

        System.out.println("当前使用的redis客户端是: " + redisConnectionFactory.getConnection());
        String value = redisTool.getValue("redis-key");
        System.out.println(value);
        redisTool.setValue("redis-key", "hello world2222");
        value = redisTool.getValue("redis-key");
        System.out.println(value);
    }
}
