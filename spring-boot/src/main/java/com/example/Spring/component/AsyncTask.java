package com.example.Spring.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncTask {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async("myTaskAsyncPool")
    public Future<Long> doTask1(int i) {
        long sum = 0;
        try {
            Thread.sleep(2500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int j = 0; j < 500000000; j++) {
            sum += j;
        }
        System.out.println("thread name is" + Thread.currentThread().getName() + "    sum=" + sum);
        logger.info("Task" + i + " started.");
        return new AsyncResult<Long>(sum);
    }

    @Async("myTaskAsyncPool")
    public void doTask2(int i) {
        long sum = 0;
        for (int j = 0; j < 500000000; j++) {
            sum += j;
        }
        System.out.println("thread name is" + Thread.currentThread().getName() + "    sum=" + sum);
        logger.info("Task" + i + " started.");
    }
}