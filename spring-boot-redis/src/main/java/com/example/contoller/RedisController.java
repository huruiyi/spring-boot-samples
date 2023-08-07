package com.example.contoller;

import com.example.entity.Country;
import com.example.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {

  private static final String KEY_NUMS = "numbers";
  private static final String KEY_REQUEST_TIME = "request_time";

//    // inject the template as ListOperations
//    //https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#redis:template
//    @Resource(name = "listOperationsV1/V2")
//    //@Resource(name = "redisTemplate")
//    private ListOperations<String, String> listOps;
  @Resource
  ValueOperations<String, String> valueOperations;
  @Resource
  RedisTemplate<Object, Object> redisTemplate;
  @Autowired
  private IRedisService redisService;
  // inject the actual template
  @Autowired
  private RedisTemplate<String, String> template;
  // inject the template as ListOperations
  @Autowired
  private ListOperations<String, String> listOps;
  @Autowired
  private ListOperations<String, String> ListOperations;
  @Resource
  private StringRedisTemplate stringRedisTemplate;

  public void addLink(String userId, URL url) {
    listOps.leftPush(userId, url.toExternalForm());
  }


  @GetMapping
  @RequestMapping("/str")
  public String getSet() {

    ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
    opsForValue.set("name", "your da ye");
    System.out.println(opsForValue.get("name"));

    valueOperations.set("name", "my da ye");
    System.out.println(valueOperations.get("name"));

    Country country = new Country();
    country.setId(1);
    country.setName("中国");
    country.setCode("CN");
    ValueOperations<Object, Object> objectValueOperations = redisTemplate.opsForValue();
    objectValueOperations.set(String.valueOf(country.getId()), country);

    Object o = objectValueOperations.get(String.valueOf(country.getId()));
    System.out.println(o);

    template.opsForValue().set(KEY_REQUEST_TIME, LocalDateTime.now().toString());
    String now = template.opsForValue().get(KEY_REQUEST_TIME);
    return now;
  }

  @GetMapping
  @RequestMapping("/list")
  public String hello() {
    listOps.leftPushAll(KEY_NUMS, "n1", "n2", "n3");

    listOps.leftPush(KEY_NUMS, "n4");
    listOps.leftPush(KEY_NUMS, "n5");
    listOps.leftPush(KEY_NUMS, "n6");
    listOps.leftPush(KEY_NUMS, "n7");

    List<String> numbersLeftPop = listOps.leftPop(KEY_NUMS, 3);
    numbersLeftPop.forEach(number -> {
      System.out.println(number);
    });

    List<String> numbersRightPop = listOps.rightPop(KEY_NUMS, 3);
    numbersRightPop.forEach(number -> {
      System.out.println(number);
    });

    String numbers = listOps.rightPop(KEY_NUMS);
    System.out.println(numbers);

    return "Hello World";
  }


  @RequestMapping(value = "/message", method = RequestMethod.GET)
  @ResponseBody
  public List<String> greeting(String user) {
    List<String> messages = redisService.listMessages(user);
    return messages;
  }

  @RequestMapping(value = "/message", method = RequestMethod.POST)
  @ResponseBody
  public String saveGreeting(String user, String message) {
    redisService.addMessage(user, message);
    return "OK";
  }
}
