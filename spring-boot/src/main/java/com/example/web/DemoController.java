package com.example.web;

import com.example.annotation.ParamsAnnotation;
import com.example.model.Course;
import com.example.model.Customer;
import com.example.model.Greeting;
import com.example.model.Order;
import com.example.service.impl.BusinessService;
import com.example.service.impl.SingleService;
import com.example.utils.ExcelUtils;
import com.example.utils.VerifyCodeUtils;
import com.example.utils.vcode.Captcha;
import com.example.utils.vcode.GifCaptcha;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
  public void test(HttpServletResponse response) {
    Map<String, String> params = new HashMap<>();
    params.put("userId", "1");
    log.info(params.toString());

    try (XSSFWorkbook xssfWorkbook = ExcelUtils.getOutputStream()) {
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
      }
    } catch (IOException e) {
      log.error(e.getMessage());
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

  @GetMapping("/sum")
  public long displaySum() {
    return businessService.calculateSum();
  }

  @RequestMapping("/courses")
  public List<Course> retrieveAllCourses() {
    return Arrays.asList(new Course(1, "Learn AWS", "in28 minutes"), new Course(2, "Learn DevOps", "in2 8minutes"),
        new Course(3, "Learn Azure", "in28 minutes"), new Course(4, "Learn GCP", "in28 minutes"));
  }

  @RequestMapping("/orders")
  public Order getOrders() {
    return Order.builder().id(1).customer(Customer.builder().id(1).lastName("ruiyi").firstName("hu").build()).orderDate(new Date())
        .productName("Acme Portal").quantity(100).build();
  }

  @RequestMapping("/sVCode")
  public void createCertCodeImageAction(HttpServletRequest request, HttpServletResponse response) {
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/jpeg");
    String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
    HttpSession session = request.getSession(true);
    session.setAttribute("v-code", verifyCode.toLowerCase());
    int w = 108, h = 34;
    try {
      VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    } catch (IOException e) {
      log.warn(e.getMessage());
    }

  }

  @RequestMapping("/dVCode")
  public void code2(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/gif");
    Captcha captcha = new GifCaptcha(108, 34, 4);
    //输出
    ServletOutputStream out = response.getOutputStream();
    captcha.out(out);
    out.flush();
    //存入Shiro会话session
    log.debug(captcha.text().toLowerCase());
    //存入会话session
    HttpSession session = request.getSession(true);
    session.setAttribute("v-code", captcha.text().toLowerCase());
  }

  @ResponseBody
  @RequestMapping(value = "/user/{userId}/roles/{roleId}", method = RequestMethod.GET)
  public String getLogin(@PathVariable("userId") String userId, @PathVariable("roleId") String roleId) {
    return "User Id : " + userId + " Role Id : " + roleId;
  }

  @ResponseBody
  @RequestMapping(value = "/javabeat/{regexp1:[a-z-]+}", method = RequestMethod.GET)
  public String getRegExp(@PathVariable("regexp1") String regexp1) {
    return "URI Part : " + regexp1;
  }

  @RequestMapping("/help")
  public void inHelpPage(HttpServletResponse response) throws IOException {
    response.reset();
    response.setContentType("application/pdf");
    response.setHeader("Content-disposition", "filename=help.pdf");
    Resource resource = new DefaultResourceLoader().getResource("classpath:pdf/java.pdf");//通过这个来加载,当项目打包成jar也可以
    File file = resource.getFile();
    try {
      if (file.exists()) {
        FileInputStream in = new FileInputStream(file);
        IOUtils.copy(in, response.getOutputStream());
        in.close();
      } else {
        log.debug("{} 文件不存在!", file);
      }
    } catch (IOException e) {
      log.debug("预览异常 {} ", e.getMessage());
    }
  }

}
