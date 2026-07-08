package com.example.web;

import com.example.annotation.ParamsAnnotation;
import com.example.model.Greeting;
import com.example.service.impl.SingleService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
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

  @RequestMapping("/sayHi")
  public Map<String, String> sayHi(String name) {
    Map<String, String> map = new HashMap<>();
    map.put("name", name);
    return map;
  }

  @RequestMapping("/greeting")
  public Greeting greeting() {
    return Greeting.builder().id(10000).content("Hello World!!!").build();
  }

  @GetMapping("/service")
  public String book() {
    String res1 = singleService1.sayHello();
    String res2 = singleService2.sayHello();
    return res1 + "<br/> " + res2;
  }

  @RequestMapping("/testMap")
  public Map<String, String> test() {
    Map<String, String> map = new HashMap<>();
    map.put("key1", "value1");
    return map;
  }

  @RequestMapping("/testAnnotation")
  @ParamsAnnotation("自定义Annotation测试")
  public String testAnnotation(@RequestParam(name = "uName", defaultValue = "Li San") String name, Integer age) {
    return "测试自定义注解,用户：" + name + "，年龄：" + age;
  }

  @RequestMapping("/p1")
  public Map<String, String> p1() {
    Map<String, String> map = new HashMap<>();
    map.put("key1", "value1");
    return map;
  }
}
