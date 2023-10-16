package com.example.web;

import com.example.service.unclassified.AsyncServiceV1;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequestMapping("/async")
public class AsyncController {

  private final AsyncServiceV1 asyncService;

  public AsyncController(AsyncServiceV1 asyncService) {
    this.asyncService = asyncService;
  }

  @RequestMapping("/test1")
  @ResponseBody
  public String asyncTaskV1() throws InterruptedException, ExecutionException {
    asyncService.reset();
    Future<Long> future = asyncService.doTask1(1);
    try {
      future.get(3, TimeUnit.SECONDS);
    } catch (TimeoutException e) {
      log.info("已经超时了！");
      log.error(e.getMessage());
    }
    log.info("All tasks finished.");
    return "test1 ok";
  }

  @RequestMapping("/test2")
  public @ResponseBody
  String asyncTaskV2() throws InterruptedException {
    asyncService.reset();
    for (int i = 0; i < 5000; i++) {
      asyncService.doTask1(1);
    }
    log.info("All tasks finished.");
    return "test2 ok";
  }

  @GetMapping("test")
  public void test1() throws InterruptedException {
    asyncService.reset();
    for (int i = 0; i < 230; i++) {
      log.info("第{}个任务加入队列", i + 1);
      asyncService.doTask3();
    }
    log.info("已全部加入队列");
  }

  @GetMapping("test2")
  public void test2() throws ExecutionException, InterruptedException {
    asyncService.reset();
    for (int i = 0; i < 230; i++) {
      log.info("第{}个任务加入队列", i + 1);
      Future<String> future = asyncService.doTask4();
      log.info("test2 result {}", future.get());
    }
    log.info("已全部加入队列");
  }

  @RequestMapping("/send")
  @ResponseBody
  public String index() throws InterruptedException {
    log.info("before @Async");
    asyncService.asyncSendMail();
    log.info("after @Async");
    return "send ok";
  }

}
