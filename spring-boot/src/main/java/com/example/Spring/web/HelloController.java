package com.example.Spring.web;

import com.example.Spring.annotation.RequestLimit;
import com.example.Spring.service.SingleService;
import com.example.Spring.utils.ExcelUtils;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/hello")
public class HelloController {

    //1：构造函数注入
    final SingleService singleService1;

    public HelloController(SingleService singleService) {
        this.singleService1 = singleService;
    }

    //2:属性注入
    @Autowired
    private SingleService singleService2;

    @Value("${spring.profiles.active}")
    private String env;

    @RequestMapping(value = "/hello")
    public String hello() {
        String res = singleService1.sayHello();
        return res;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Map<String, String> test() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/env")
    public String env() {
        return "Hello," + env;
    }


    @RequestMapping("/session")
    @ResponseBody
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

    @RequestMapping(value = "/devtools")
    @ResponseBody
    public String dev() {
        return "Hello World v2.1.1";
    }

    @RequestMapping(value = "/beans")
    @ResponseBody
    public String[] bean() {
        String[] beans = singleService1.getBeans();
        return beans;
    }

}
