package com.example.Spring.web;

import com.example.Spring.annotation.ParamsAnnotation;
import com.example.Spring.annotation.SysLog;
import com.example.Spring.service.unclassified.SingleService;
import com.example.Spring.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@EnableAspectJAutoProxy
public class IndexController {

    //1：构造函数注入
    final SingleService singleService1;

    public IndexController(SingleService singleService) {
        this.singleService1 = singleService;
    }

    //2:属性注入
    @Autowired
    private SingleService singleService2;

    @Value("${spring.profiles.active}")
    private String env;


    @SysLog
    @RequestMapping(value = "/")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/sayHi")
    public Map<String, String> hello(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }

    @GetMapping("/service")
    public String book() {
        String res1 = singleService1.sayHello();
        String res2 = singleService2.sayHello();
        return res1 + "<br/> " + res2;
    }


    @RequestMapping("/testMap")
    @ResponseBody
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

    @ResponseBody
    @RequestMapping("/p1/p2")
    public Map<String, String> p1p2() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/env")
    public String env() {
        return "Hello," + env;
    }


    @ResponseBody
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
            e.printStackTrace();
        } finally {
            try {
                if (xssfWorkbook != null) {
                    xssfWorkbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/devtools")
    public String dev() {
        return "Hello World v2.1.1";
    }

    @RequestMapping(value = "/beans")
    @ResponseBody
    public String[] bean() {
        return singleService1.getBeans();
    }

}
