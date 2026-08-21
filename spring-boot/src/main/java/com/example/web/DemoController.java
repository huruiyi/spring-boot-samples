package com.example.web;

import com.example.annotation.ParamsAnnotation;
import com.example.model.Greeting;
import com.example.service.impl.SingleService;
import com.example.utils.SecureClientIpResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@EnableAspectJAutoProxy
public class DemoController {

  /**
   * 可信代理 CIDR（负载均衡 / 反向代理）。生产环境请替换为真实代理网段，不要包含公网客户端网段。
   */
  private static final List<String> TRUSTED_PROXY_CIDRS = Arrays.asList(
      "127.0.0.1/32",
      "::1/128"
  );

  private final SecureClientIpResolver clientIpResolver =
      new SecureClientIpResolver(TRUSTED_PROXY_CIDRS);

  @PostConstruct
  public void logSomething() {
    log.debug("Sample Debug Message");
    log.trace("Sample Trace Message");
  }

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

  @GetMapping("/secure-real-ip")
  public String getSecureRealIp(HttpServletRequest request) {
    return "Secure real IP: " + clientIpResolver.resolve(request);
  }

  @GetMapping("/request-info")
  public Map<String, String> getRequestInfo(HttpServletRequest request) {
    Map<String, String> info = new LinkedHashMap<>();
    info.put("remoteAddr", request.getRemoteAddr());
    info.put("xForwardedFor", request.getHeader("X-Forwarded-For"));
    info.put("xRealIP", request.getHeader("X-Real-IP"));
    info.put("secureRealIP", clientIpResolver.resolve(request));
    return info;
  }

}