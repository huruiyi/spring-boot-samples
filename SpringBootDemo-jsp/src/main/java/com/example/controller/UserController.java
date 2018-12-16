package com.example.controller;


import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public ModelAndView mvc1() {
        return new ModelAndView("index");
    }

    @RequestMapping("/helloworld")
    public String index() {
        return "Hello World";
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
}
