package com.example.web;

import com.example.service.impl.BusinessService;
import com.example.service.impl.SingleService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/runtime")
public class RuntimeController {

  private final SingleService singleService;
  private final BusinessService businessService;

  @Value("${spring.profiles.active}")
  private String env;

  public RuntimeController(SingleService singleService, BusinessService businessService) {
    this.singleService = singleService;
    this.businessService = businessService;
  }

  @RequestMapping(value = "/env")
  public String env() {
    return "Hello," + env;
  }

  @RequestMapping("/session")
  public String sessionTrack(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.setAttribute("sessionKey", "sessionValue");
    return "hello world";
  }

  @RequestMapping(value = "/devtools")
  public String dev() {
    return "Hello World v2.1.1";
  }

  @RequestMapping(value = "/beans")
  public String[] bean() {
    return singleService.getBeans();
  }

  @GetMapping("/sum")
  public long displaySum() {
    return businessService.calculateSum();
  }
}
