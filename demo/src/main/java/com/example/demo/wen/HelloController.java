package com.example.demo.wen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Value("${spring.profiles.active}")
    private String env;

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hello," + env;
    }
}
