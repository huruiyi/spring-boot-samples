package com.example.Spring.web;

import com.example.Spring.annotation.RequestLimit;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@Slf4j
public class RateController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RateController.class);

    private RateLimiter rateLimiter = RateLimiter.create(10);

    @ResponseBody
    @RequestMapping(value = "/limit")
    private String guavaLimit() {
        if (rateLimiter.tryAcquire()) {
            System.out.println(Instant.now());
            return "Acquire 。。。";
        } else {
            System.out.println("false:" + Instant.now());
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
