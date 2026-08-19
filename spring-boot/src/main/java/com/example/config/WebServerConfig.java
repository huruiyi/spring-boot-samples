package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class WebServerConfig  implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

  @Autowired
  private Environment env;

  //jar包: 执行SpringBootApplication的run方法,启动IOC容器,然后创建嵌入式Servlet容器
  //war包: 先是启动Servlet服务器,服务器启动Springboot应用(springBootServletInitizer),然后启动IOC容器
  @Override
  public void customize(ConfigurableServletWebServerFactory factory) {
    Integer configuredPort = env.getProperty("server.port", Integer.class);
    if (configuredPort != null) {
      factory.setPort(configuredPort);
    }
  }

}
