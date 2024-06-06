package com.example.batch;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class BatchDemoApplication {

  public static void main(String[] args) throws IOException {
    ConfigurableApplicationContext ctx = SpringApplication.run(BatchDemoApplication.class, args);
    assert (ctx != null);
    log.info("Application started...");
    int read = System.in.read();
    log.info(String.valueOf(read));
    ctx.close();
  }

}
