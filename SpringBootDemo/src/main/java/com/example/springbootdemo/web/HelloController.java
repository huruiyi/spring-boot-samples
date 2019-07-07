package com.example.springbootdemo.web;

import com.example.springbootdemo.service.HelloService;
import com.example.springbootdemo.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 公司：TBK
 * 作者：胡睿毅
 * 文件名：HelloController
 * 日期：2019/4/26 8:44
 **/
@RestController
public class HelloController {

    @Value("${spring.profiles.active}")
    private String env;


    @Autowired
    HelloService helloService;


    @ResponseBody
    @RequestMapping(value = "/env")
    public String env() {
        return "Hello," + env;
    }


    @RequestMapping(value = "/hello")
    public String hello() {
        String res = helloService.sayHello();
        return res;
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
}
