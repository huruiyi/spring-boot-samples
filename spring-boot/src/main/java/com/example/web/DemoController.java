package com.example.web;

import com.example.annotation.ParamsAnnotation;
import com.example.model.Course;
import com.example.service.impl.BusinessService;
import com.example.service.unclassified.SingleService;
import com.example.utils.ExcelUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo")
@EnableAspectJAutoProxy
public class DemoController {

  //1：构造函数注入
  final SingleService singleService1;

  //2:属性注入
  @Autowired
  private SingleService singleService2;

  @Value("${spring.profiles.active}")
  private String env;

  public DemoController(SingleService singleService) {
    this.singleService1 = singleService;
  }

  @Autowired
  private BusinessService businessService;


  @RequestMapping("/sayHi")
  public Map<String, String> sayHi(String name) {
    Map<String, String> map = new HashMap<>();
    map.put("name", name);
    return map;
  }
  @RequestMapping("/sayHello")
  public String sayHello(String name) {
    return "hello " + name;
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

  @RequestMapping(value = "/env")
  public String env() {
    return "Hello," + env;
  }


  @RequestMapping("/session")
  public String sessionTrack(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.setAttribute("sessionKey", "sessionValue");
    return "hello world";
  }

  @RequestMapping(value = "/exportExcel")
  public void test(HttpServletResponse response) throws UnsupportedEncodingException {
    Map<String, String> params = new HashMap<>();
    params.put("userId", "1");
    log.info(params.toString());

    XSSFWorkbook xssfWorkbook = ExcelUtils.getOutputStream();
    try {
      SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddhhmmss");
      String fileName = sd.format(new Date()) + "预付款明细.xlsx";
      OutputStream output = response.getOutputStream();
      response.reset();
      response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
      response.setContentType("application/msexcel");
      xssfWorkbook.write(output);
      output.close();
    } catch (IOException e) {
      log.error(e.getMessage());
    } finally {
      try {
        if (xssfWorkbook != null) {
          xssfWorkbook.close();
        }
      } catch (IOException e) {
        log.error(e.getMessage());
      }
    }
  }

  @RequestMapping(value = "/devtools")
  public String dev() {
    return "Hello World v2.1.1";
  }

  @RequestMapping(value = "/beans")
  public String[] bean() {
    return singleService1.getBeans();
  }

  public long returnValueFromBusinessService() {
    return businessService.calculateSum();
  }

  @GetMapping("/sum")
  public long displaySum() {
    return businessService.calculateSum();
  }

  @RequestMapping("/courses")
  public List<Course> retrieveAllCourses() {
    return Arrays.asList(
        new Course(1, "Learn AWS", "in28 minutes"),
        new Course(2, "Learn DevOps", "in2 8minutes"),
        new Course(3, "Learn Azure", "in28 minutes"),
        new Course(4, "Learn GCP", "in28 minutes")
    );
  }


}
