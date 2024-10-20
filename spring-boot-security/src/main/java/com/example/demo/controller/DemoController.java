package com.example.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.demo.utils.AesUtils;
import com.example.demo.utils.RsaUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

  private static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANdAeSfh1fehSmmqvZWPukEQOh7lj7Am5MWpUiYVTvHIMcLPBVjjSrz/Y4KagyeueM5EvpiHtRmX0ZD8U68kcCdesKVTaYxEPOFMb23Dia+x8OT8Qekn6gINSaVKqnWsbvyZzz2uNeqhpHNKx2pgH1uekEdWMbyvWCZxGoq8Km69AgMBAAECgYEAj+d0mCstRv43p2kUkfaiyHi2wo0qgNOfwl2uo/M+8fmq+tg4dPKPtsbumhP+dvr3nL7sxUCE3HVZD5sBv2eW6iFCW8TRm9z3OHmcBPWPoCoc9BtWwXcm6MA29J1KJqvsGNLwGUcRtoMcYE/OWed1hCrp4kn9AI6LhgPt75rj8qkCQQDuxSJrc1x2TIiQfkAYFiw5U6SldDNEM0PRy2qZv2+cSHCuMrDmr0b2nBPj0RR6Q9yvQbYVUfR4LGCD6Cd1l5q3AkEA5sjhY+qLMghjMyJNUTXZceBxc99o5H6d8CUe16zeCfbdQkeI5JUnzk6Mjg8Wcf8XxTboi/uRUYWcKjJg+v4eKwJAM5ezLUabFxDIfXhaPxojaiuxqvKl1TnCkMWEfj5ITpu0hV98rAv5qHXnMlXON/EL8W6gepDf40urezUhuZ4NlwJBAOKFLWVq4zE6tlOMSaN6XXG+wNzg3g3YkaESblF3JYFWQxo5KI5kMGv5AVC2UmuV3HkASgSL6bjAkeWBCVuSbX0CQC5xBG/83h0Q1Rnozv1hfj0pQL9QDBsxYKRl0XpyjUbCBF/F0oAv1DfUEOwHbCkgDlBl5U8jSHXvkvOOd8Qe18U=";

  @GetMapping
  public String rsaPage() {
    return "rsa";
  }

  @RequestMapping(value = "/login")
  public String login() {
    return "login";
  }


  @GetMapping("/key")
  private @ResponseBody String create16String() {
    return RandomUtil.randomString(16);
  }


  @GetMapping("/rsaDecrypt")
  public @ResponseBody String DecryptAction(String data) throws Exception {
    System.out.println(data);
    return RsaUtils.decryptByPrivateKey(privateKey, data);
  }


  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public  @ResponseBody Object login(HttpServletRequest request) {
    String encryptedPassword = request.getParameter("password");
    String key = request.getParameter("key");
    return AesUtils.decrypt(encryptedPassword, key);
  }

}
