package com.example.web;

import com.example.annotation.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

  @SysLog
  @GetMapping
  public String index() {
    return "<p style='color:red;text-align: center;'>Hello World</p>";
  }

}
