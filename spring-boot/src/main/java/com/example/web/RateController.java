package com.example.web;

import com.example.annotation.RequestLimit;
import com.google.common.util.concurrent.RateLimiter;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rate")
public class RateController {

  private RateLimiter rateLimiter = RateLimiter.create(10);

  @ResponseBody
  @RequestMapping(value = "/limit")
  private String limit() {
    if (rateLimiter.tryAcquire()) {
      log.info("true:" + Instant.now());
      return "Acquire 。。。";
    } else {
      log.info("false:" + Instant.now());
      return "Hello World";
    }
  }

  @GetMapping(value = "/testLimit")
  @RequestLimit
  public String testLimit() {
    log.info("开始测试限流");
    return "success";
  }

}
