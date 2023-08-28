package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {


  @ResponseBody
  @RequestMapping("/")
  public String index() {
    return "首页！！";
  }

  @ResponseBody
  @RequestMapping("/welcome")
  public String welcome() {
    return "热烈欢迎你！！";
  }

}
