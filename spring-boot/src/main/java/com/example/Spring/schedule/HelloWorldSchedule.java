package com.example.Spring.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldSchedule implements SchedulingConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldSchedule.class);

    @Scheduled(fixedRate = 4000L)
    public void printMessage() {
        logger.info("Hello World, from Spring Boot 2!");
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addFixedRateTask(() -> printMessage(), 4000);
    }
}
