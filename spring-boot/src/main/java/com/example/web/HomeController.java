package com.example.web;

import com.example.annotation.SysLog;
import com.example.service.impl.CurrencyService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

  @Value("${server.port}")
  private String port;

  private final CurrencyService currencyService;
  private final Environment environment;

  public HomeController(CurrencyService currencyService, Environment environment) {
    this.currencyService = currencyService;
    this.environment = environment;
  }

  @SysLog
  @GetMapping("/")
  public ModelAndView index() {
    ModelAndView mav = new ModelAndView("index");
    mav.addObject("controllerCount", 15);
    mav.addObject("endpointCount", 59);
    mav.addObject("port", port);
    String activeProfiles = String.join(", ", environment.getActiveProfiles());
    mav.addObject("activeProfile", activeProfiles.isEmpty() ? "default" : activeProfiles);
    return mav;
  }

  @RequestMapping("/sayHello")
  public String sayHello(String name) {
    return "hello " + name;
  }

  @GetMapping("/session-set")
  public String set(HttpSession session) {
    session.setAttribute("user", "fairy");
    return String.valueOf(port);
  }

  @GetMapping("/session-get")
  public String get(HttpSession session) {
    return session.getAttribute("user") + ":" + port;
  }

  @RequestMapping("/currency-configuration")
  public String currencyService() {
    return currencyService.toString();
  }
}
