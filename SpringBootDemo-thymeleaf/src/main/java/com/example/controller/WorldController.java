package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorldController {
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String greet() {
        return "Hello World";
    }

    @RequestMapping(value = "/world")
    public String world() {
        return "world";
    }
}
