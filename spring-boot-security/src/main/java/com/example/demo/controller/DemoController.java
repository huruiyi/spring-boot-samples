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

  private static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIEqnj4kbyY//J+OQ89jBmSfKDavCDJCLmTmj6ilQAeMSmCwvqmrcOmBFiv/dD4ffp3+SSQBvEKGkGYI1llgW1wy6luxgainN4ANvaWTpHrwsfzDLreMmsxye9/Aq4GxRPufQuEj4iloOrUydt2XpdR0wUwEIKo0TuppkUjGgM5JAgMBAAECgYACSom9C3NnR8pI4EKmqpiB72rMsYUhMxhUlNxmfuvoVl45Ydk39EEP+0iz5hefRHq//bM5bshym6VzEeQPeMyWCxTfeUp4sashSgVV7wi49P8ekceWiudKTgJSHlNNgBotBxe6EFc3J1SIvQfMl9z8piNWu/W0kV4Xn6JkDgmPUwJBAM/dlx+ent2X0qmCY/XKX5CWGo2FYVjbl8E95y+SIyetRdPisNt+NNaJtJ1EfvdO4KMrc03+5BmydlrSNrSJCG8CQQCfE7FD1SsCXiwzwbUl6RVhojNFX0YEqF4jEMx92P6nnPd4MEHilu+23c0Jytdg5mt6YgOWbyxgtuGQj1o02MDHAkBWb5qL93WwR5W+Dw5qAsFCamiYOMKGJKoyHP+pdGdR9Gd4+Gb6nrxyCcklLHpvdElNEKBd6oQHe4Jjuc4tvF3ZAkAOfaq+NSLv2khIhrLLu0nZVP86qI0mS3gX1aXrKOM5vZDFgA/gZkN78wSEidfI2fScd2VAROxT+xTdKFHdUoltAkAmdId1M/nI4ncoM9gOq7UGpJr6881i235kDouQD676FBe/L1QAq2B8iIXsyRvcvFrR1CDzzqt3KYCYzNdnkjT3";

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
