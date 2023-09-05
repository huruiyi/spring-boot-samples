package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  /**
   * <a href="http://localhost:9401/user/getCurrentUser?access_token=fJ29Voj_gJwGRjsif_hULcRrOzE">...</a>
   */
  @ResponseBody
  @GetMapping("/getCurrentUser")
  public Object getCurrentUser(Authentication authentication) {
    return authentication.getPrincipal();
  }

}
