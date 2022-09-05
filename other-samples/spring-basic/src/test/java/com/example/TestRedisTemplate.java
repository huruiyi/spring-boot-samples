package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-redis.xml")
public class TestRedisTemplate {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        System.out.println(redisTemplate);
    }

    @Test
    public void test01() {
        ValueOperations operations = redisTemplate.opsForValue();
        String key = "name";
        operations.set("name", "zhangsan");
        Object name = operations.get("name");
        System.out.println("name:" + name);
        operations.append("name", "is man");
        operations.getAndSet("name", "zhangsan-1");
        Object name1 = operations.get("name");
        System.out.println("修改后：" + name1);

    }
}
