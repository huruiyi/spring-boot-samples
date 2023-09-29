package com.example.web;

import com.example.service.unclassified.RedisService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

  private final RedisService redisService;

  private final RedisTemplate<String, String> redisTemplate;

  private final StringRedisTemplate stringRedisTemplate;


  public RedisController(RedisService redisService, RedisTemplate<String, String> redisTemplate, StringRedisTemplate stringRedisTemplate) {
    this.redisService = redisService;
    this.redisTemplate = redisTemplate;
    this.stringRedisTemplate = stringRedisTemplate;
  }


  @RequestMapping("/setObject")
  public String setString(String key, String value) {
    redisService.setObject(key, value, 30l);
    return "success";
  }

  @RequestMapping("/setList")
  public String setList(String key) {
    List<String> listValue = new ArrayList<>();
    listValue.add("user01");
    listValue.add("user02");
    redisService.setObject(key, listValue);
    return "success";
  }


  @RequestMapping("/setString")
  @ResponseBody
  public Map<String, Object> StringOp() {
    redisTemplate.opsForValue().set("key1", "value1");

    // 注意这里使用了JDK的序列化器，所以Redis保存的时候不是整数，不能运算
    redisTemplate.opsForValue().set("int_key", "1");
    stringRedisTemplate.opsForValue().set("int", "1");
    // 使用运算
    stringRedisTemplate.opsForValue().increment("int", 1);

    // 获取底层Jedis连接
    // 减一操作,这个命令RedisTemplate不支持，所以笔者先获取底层的连接再操作
    // Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
    // jedis.decr("int");

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("success", true);
    return map;
  }

  @RequestMapping("/getString")
  public String getString(String key) {
    return redisService.getString(key);
  }

  @RequestMapping("/hash")
  @ResponseBody
  public String hashOp() {

    Map<String, String> hash = new HashMap<String, String>();
    hash.put("field1", "value1");
    hash.put("field2", "value2");

    stringRedisTemplate.opsForHash().putAll("hash", hash);
    stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
    BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
    hashOps.delete("field1", "field2");
    hashOps.put("filed4", "value5");
    return "ok";
  }

}
