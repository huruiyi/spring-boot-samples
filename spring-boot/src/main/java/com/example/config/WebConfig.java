package com.example.config;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class WebConfig implements ApplicationListener<WebServerInitializedEvent> {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());


  @Override
  public void onApplicationEvent(WebServerInitializedEvent event) {
    try {
      InetAddress inetAddress = Inet4Address.getLocalHost();
      int port = event.getWebServer().getPort();
      logger.info("项目启动成功！地址端口(start port): http://" + inetAddress.getHostAddress() + ":" + port);

    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }
}
