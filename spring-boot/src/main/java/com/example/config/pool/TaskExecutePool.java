package com.example.config.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TaskExecutePool {

  @Autowired
  private TaskThreadPoolConfig taskConfig;

  @Bean
  public Executor myTaskAsyncPool() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(taskConfig.getCorePoolSize());
    executor.setMaxPoolSize(taskConfig.getMaxPoolSize());
    executor.setQueueCapacity(taskConfig.getQueueCapacity());
    executor.setKeepAliveSeconds(taskConfig.getKeepAliveSeconds());
    executor.setThreadNamePrefix("ThreadName-");

    // rejection-policy：当pool已经达到max size的时候，如何处理新任务
    // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.initialize();
    return executor;
  }
}