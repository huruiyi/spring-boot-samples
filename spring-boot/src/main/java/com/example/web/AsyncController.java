package com.example.web;

import com.example.service.impl.AsyncServiceV1;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

  private final Long TASK_COUNT = 100L;
  private final AsyncServiceV1 asyncService;

  public AsyncController(AsyncServiceV1 asyncService) {
    this.asyncService = asyncService;
  }

  @RequestMapping("/test1")
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
  public String asyncTaskV2() throws InterruptedException {
    asyncService.reset();
    for (int i = 0; i < TASK_COUNT; i++) {
      asyncService.doTask1(i);
    }
    log.info("All tasks finished.");
    return "test2 ok";
  }

  @GetMapping("/test3")
  public void test3() throws InterruptedException {
    asyncService.reset();
    for (int i = 0; i < TASK_COUNT; i++) {
      log.info("第{}个任务加入队列", i + 1);
      asyncService.doTask3();
    }
    log.info("已全部加入队列");
  }

  @GetMapping("/test4")
  public void test4() throws ExecutionException, InterruptedException {
    asyncService.reset();
    for (int i = 0; i < TASK_COUNT; i++) {
      log.info("第{}个任务加入队列", i + 1);
      Future<String> future = asyncService.doTask4();
      log.info("test4 result {}", future.get());
    }
    log.info("已全部加入队列");
  }

  @RequestMapping("/send")
  public String index() throws InterruptedException {
    log.info("before @Async");
    asyncService.asyncSendMail();
    log.info("after @Async");
    return "send ok";
  }

}
