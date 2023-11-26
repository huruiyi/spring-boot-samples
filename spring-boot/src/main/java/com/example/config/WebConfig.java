package com.example.config;

import com.example.exception.BusinessException;
import com.example.enums.HttpStatusCode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@Slf4j
@EnableAsync
@Configuration
@EnableScheduling
public class WebConfig implements ApplicationListener<WebServerInitializedEvent> {

  @Override
  public void onApplicationEvent(WebServerInitializedEvent event) {
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      int port = event.getWebServer().getPort();
      log.info("项目启动成功！地址端口(start port): http://{}:{}",  inetAddress.getHostAddress(), port);
    } catch (UnknownHostException e) {
      throw new BusinessException(HttpStatusCode.INTERNAL_SERVER_ERROR,e.getMessage());
    }
  }

  @Bean
  //@ConditionalOnMissingBean     //当没有这个 bean 的时候才会创建
  //@ConditionalOnEnabledEndpoint //当开启 EndPoint 的时候才会注入,as of 2.2.0 in favor of @ConditionalOnAvailableEndpoint
  @ConditionalOnAvailableEndpoint
  public DateTimeEndPoint dateTimeEndPoint() {
    return new DateTimeEndPoint();
  }

}
