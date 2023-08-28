package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by macro on 2019/9/30.
 */
@Controller
@RequestMapping("/user")
public class UserController {

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  /**
   * http://localhost:9401/user/getCurrentUser?access_token=fJ29Voj_gJwGRjsif_hULcRrOzE
   */
  @ResponseBody
  @GetMapping("/getCurrentUser")
  public Object getCurrentUser(Authentication authentication) {
    return authentication.getPrincipal();
  }

}
