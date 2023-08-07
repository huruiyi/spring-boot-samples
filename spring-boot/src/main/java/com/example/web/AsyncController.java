package com.example.web;

import com.example.component.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Controller
@RequestMapping("/async")
public class AsyncController {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AsyncService asyncService;

  @RequestMapping("/test1")
  @ResponseBody
  public String AsyncTaskTest() throws InterruptedException, ExecutionException {
    Future<Long> future = asyncService.doTask1(1);
    try {
      future.get(2, TimeUnit.SECONDS);
    } catch (TimeoutException e) {
      System.out.println("已经超时了！");
      e.printStackTrace();
    }
    logger.info("All tasks finished.");
    return "test1 ok";
  }

  @RequestMapping("/test2")
  public @ResponseBody
  String AsyncTaskTest1() throws InterruptedException, ExecutionException {
    for (int i = 0; i < 5000; i++) {
      asyncService.doTask1(1);
    }
    logger.info("All tasks finished.");
    return "test2 ok";
  }

  @GetMapping("test")
  public void test(HttpServletRequest request) {
    asyncService.i = new AtomicInteger(0);
    for (int i = 0; i < 230; i++) {
      System.out.println("第" + (i + 1) + "个任务加入队列");
      asyncService.doTask3();
    }
    System.out.println("已全部加入队列");
  }

  @GetMapping("test2")
  public void test2(HttpServletRequest request) throws ExecutionException, InterruptedException {
    asyncService.i = new AtomicInteger(0);
    for (int i = 0; i < 230; i++) {
      System.out.println("第" + (i + 1) + "个任务加入队列");
      Future<String> future = asyncService.doTask4();
      System.out.println(future.get());
    }
    System.out.println("已全部加入队列");
  }

  @RequestMapping("/send")
  @ResponseBody
  public String index() {
    log.info("before @Async");
    asyncService.asyncSendMail();
    log.info("after @Async");
    return "send ok";
  }

}
