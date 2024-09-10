package com.example;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

@Slf4j
@SpringBootTest
public class RedisTest {

  @Autowired
  RedisTemplate<String, String> redisTemplate;

  @Autowired
  RedisTemplate<String, Integer> intRedisTemplate;

  @Autowired
  RedisTemplate<String, Object> objectRedisTemplate;

  @Test
  void test0() {
    redisTemplate.opsForValue().increment("count");
    Integer count = intRedisTemplate.opsForValue().get("count");
    log.info("count={}", count);
  }

  @Test
  void test1() {
    redisTemplate.opsForValue().set("name", "si si you");
    log.info("name={}", redisTemplate.opsForValue().get("name"));
  }

  @Test
  void testLuaString() {
    redisTemplate.opsForValue().set("name", "bala bala");

    String LUA_SCRIPT = "return redis.call('get', KEYS[1])";
    // 执行Lua脚本
    DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
    redisScript.setScriptText(LUA_SCRIPT);
    redisScript.setResultType(String.class);
    Object result = redisTemplate.opsForValue().getOperations().execute(redisScript, Collections.singletonList("name"));
    System.out.println("脚本执行结果: " + result);
  }


  public boolean checkAndSet(String expectedValue, String newValue) {
    RedisScript<Boolean> script0 = RedisScript.of(new DefaultResourceLoader().getResource("checkandset.lua"), Boolean.class);

    DefaultRedisScript<Boolean> script1 = new DefaultRedisScript<>();
    script1.setLocation(new ClassPathResource("checkandset.lua"));
    script1.setResultType(Boolean.class);

    Boolean key = objectRedisTemplate.execute(script0, Collections.singletonList("key"), expectedValue, newValue);
    return Boolean.TRUE.equals(key);
  }

  @Test
  void testLuaBool() {
    boolean b = checkAndSet("ha-ha", "ha-ha-2");
    System.out.println(b);
  }

  @Test
  void testLuaList() {
    objectRedisTemplate.opsForList().leftPushAll("users", "u1", "u2", "张三", "李四");

    RedisScript<List> script = RedisScript.of(new DefaultResourceLoader().getResource("people.lua"), List.class);
    List list = objectRedisTemplate.execute(script, Collections.singletonList("users"), Collections.emptyList());
    list.forEach(item -> log.info("users:{}", item.toString()));
  }

}
