package com.example.controller;


import com.example.utils.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {


  @ResponseBody
  @PreAuthorize("hasAuthority('admin')")
  @RequestMapping("/welcome")
  public String welcome() {
    return UserInfo.getUser().getUsername() + "：欢迎你！！";
  }

}
