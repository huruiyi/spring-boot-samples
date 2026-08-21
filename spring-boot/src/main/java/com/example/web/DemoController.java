package com.example.web;

import com.example.annotation.ParamsAnnotation;
import com.example.model.Greeting;
import com.example.service.impl.SingleService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAspectJAutoProxy
public class DemoController {

  //1：构造函数注入
  final SingleService singleService1;

  //2:属性注入
  @Autowired
  private SingleService singleService2;

  public DemoController(SingleService singleService) {
    this.singleService1 = singleService;
  }

  @RequestMapping("/demo/sayHi")
  public Map<String, String> sayHi(String name) {
    Map<String, String> map = new HashMap<>();
    map.put("name", name);
    return map;
  }

  @RequestMapping("/demo/greeting")
  public Greeting greeting() {
    return Greeting.builder().id(10000).content("Hello World!!!").build();
  }

  @GetMapping("/demo/service")
  public String book() {
    String res1 = singleService1.sayHello();
    String res2 = singleService2.sayHello();
    return res1 + "<br/> " + res2;
  }

  @RequestMapping("/demo/testMap")
  public Map<String, String> test() {
    Map<String, String> map = new HashMap<>();
    map.put("key1", "value1");
    return map;
  }

  @RequestMapping("/demo/testAnnotation")
  @ParamsAnnotation("自定义Annotation测试")
  public String testAnnotation(@RequestParam(name = "name", defaultValue = "Li San") String name, Integer age) {
    return "测试自定义注解,用户：" + name + "，年龄：" + age;
  }

  @RequestMapping("/demo/p1")
  public Map<String, String> p1() {
    Map<String, String> map = new HashMap<>();
    map.put("key1", "value1");
    return map;
  }

  /**
   * 给 SpringFramework7.0_Tutorials 当下游：POST /randomNumberGenerator/from/{from}/to/{to}
   */
  @PostMapping("/randomNumberGenerator/from/{from}/to/{to}")
  public String generateRandomNumber(
      @PathVariable("from") int from,
      @PathVariable("to") int to,
      @RequestBody(required = false) String body,
      @RequestParam(value = "defaultResp", required = false) boolean defaultResp) {
    if (defaultResp) {
      return StringUtils.isBlank(body) ? "default" : body;
    }
    int min = Math.min(from, to);
    int max = Math.max(from, to);
    return String.valueOf(ThreadLocalRandom.current().nextInt(min, max + 1));
  }
}
