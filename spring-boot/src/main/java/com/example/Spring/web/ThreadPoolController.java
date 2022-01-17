package com.example.Spring.web;

import com.example.Spring.component.AsyncTask;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/pool")
public class ThreadPoolController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncTask asyncTask;

    @RequestMapping("/test1")
    @ResponseBody
    public String AsyncTaskTest() throws InterruptedException, ExecutionException {
        Future<Long> future = asyncTask.doTask1(1);
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
            asyncTask.doTask1(1);
        }
        logger.info("All tasks finished.");
        return "test2 ok";
    }

    @GetMapping("test")
    public void test(HttpServletRequest request) {
        asyncTask.i = new AtomicInteger(0);
        for (int i = 0; i < 230; i++) {
            System.out.println("第" + (i + 1) + "个任务加入队列");
            asyncTask.doTask3();
        }
        System.out.println("已全部加入队列");
    }

    @GetMapping("test2")
    public void test2(HttpServletRequest request) throws ExecutionException, InterruptedException {
        asyncTask.i = new AtomicInteger(0);
        for (int i = 0; i < 230; i++) {
            System.out.println("第" + (i + 1) + "个任务加入队列");
            Future<String> future = asyncTask.doTask4();
            System.out.println(future.get());
        }
        System.out.println("已全部加入队列");
    }

}
