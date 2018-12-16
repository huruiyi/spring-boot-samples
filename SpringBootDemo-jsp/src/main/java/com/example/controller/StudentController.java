package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @RequestMapping(value = "/index")
    public String index() {
        return "/student/index";
    }


    @RequestMapping(value = "/greet")
    @ResponseBody
    public String greet() {
        return "Hello World";
    }
}

