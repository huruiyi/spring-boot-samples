package com.example.web;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

  @Value("${server.port}")
  private String port;

  @GetMapping("/set")
  public String set(HttpSession session) {
    session.setAttribute("user", "fairy");
    return String.valueOf(port);
  }

  @GetMapping("/get")
  public String get(HttpSession session) {
    return session.getAttribute("user") + ":" + port;
  }
}
