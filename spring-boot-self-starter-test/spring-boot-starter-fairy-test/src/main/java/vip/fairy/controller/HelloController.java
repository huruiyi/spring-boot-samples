package vip.fairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.fairy.ano.PrintLog;
import vip.fairy.service.MD5Service;

@Controller
public class HelloController {

  @Autowired
  MD5Service md5Service;

  @PrintLog
  @ResponseBody
  @RequestMapping("/hello")
  public String hello() {
    return md5Service.getMD5("Hello World,世界你好！");
  }

}
