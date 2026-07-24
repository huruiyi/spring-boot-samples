package vip.fairy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vip.fairy.model.SysUser;

@Controller
@RequestMapping("auth")
public class AuthController {

  @GetMapping("loginPage")
  public String loginPage(ModelAndView mv) {
    mv.addObject("user", new SysUser());
    return "login";
  }

  @PostMapping("login")
  public String login(SysUser user) {
    System.out.println(user);
    return "home";
  }
}
