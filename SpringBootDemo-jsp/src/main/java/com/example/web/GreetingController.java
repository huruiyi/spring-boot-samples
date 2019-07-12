package com.example.web;

import com.alibaba.fastjson.JSONObject;
import com.example.Model.Greeting;
import com.example.Model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    @Autowired
    private UserService userService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    /*
        http://localhost:8080/get?id=1
    */
    @RequestMapping("/get")
    @ResponseBody
    public String get(User user) {
        User u = userService.getNameById(user);
        return JSONObject.toJSONString(u);
    }


    @RequestMapping("/index")
    public ModelAndView mvc1() {
        return new ModelAndView("index");
    }

    @RequestMapping("/helloworld")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/test")
    @ResponseBody
    public Map<String, String> test() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        return map;
    }

}