package com.example.config;

import com.example.enums.HttpStatusCode;
import com.example.exception.BusinessException;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.OncePerRequestFilter;
import org.wildfly.common.annotation.NotNull;


@Slf4j
@EnableAsync
@Configuration
@EnableScheduling
public class WebConfig implements ApplicationListener<WebServerInitializedEvent> {

  @Autowired
  private Environment env;

  @Autowired
  private ConfigurableApplicationContext context;

  /**
   * 强制 HTTPS 重定向配置
   */
  @Bean
  public OncePerRequestFilter httpsRedirectFilter() {
    return new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
          throws ServletException, IOException {
        if (!request.isSecure()) {
          String httpsUrl = "https://" + request.getServerName() + ":443" + request.getRequestURI();
          response.sendRedirect(httpsUrl);
          return;
        }
        filterChain.doFilter(request, response);
      }
    };
  }

  @Override
  public void onApplicationEvent(WebServerInitializedEvent event) {
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      int port = event.getWebServer().getPort();
      log.info("项目启动成功！地址端口(start port): https://{}:{}", inetAddress.getHostAddress(), port);

      printApplicationStartupInfo();
    } catch (UnknownHostException e) {
      throw new BusinessException(HttpStatusCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  private void printApplicationStartupInfo() {
    // 获取进程ID
    String processName = ManagementFactory.getRuntimeMXBean().getName();
    String pid = processName.split("@")[0];

    // 获取应用访问地址
    String protocol = "http";
    if (env.getProperty("server.ssl.key-store") != null) {
      protocol = "https";
    }
    //不准确， 获取的端口号可能不是启动端口（以onApplicationEvent中的为主）
    String serverPort = env.getProperty("server.port", "8090");
    String contextPath = env.getProperty("server.servlet.context-path", "");
    if (contextPath.trim().isEmpty()) {
      contextPath = "/";
    }

    // 获取主机信息
    String hostAddress = "localhost";
    try {
      hostAddress = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      System.out.println("无法获取主机地址: " + e.getMessage());
    }

    // 打印启动信息
    System.out.println("\n----------------------------------------------------------");
    System.out.println("应用 '" + env.getProperty("spring.application.name", "application") + "' 已启动!");
    System.out.println("进程 ID: " + pid);
    System.out.println("访问URL:");
    System.out.println("  Local: \t" + protocol + "://localhost:" + serverPort + contextPath);
    System.out.println("  External: \t" + protocol + "://" + hostAddress + ":" + serverPort + contextPath);
    System.out.println("----------------------------------------------------------\n");
  }

  @Bean
  //@ConditionalOnMissingBean     //当没有这个 bean 的时候才会创建
  //@ConditionalOnEnabledEndpoint //当开启 EndPoint 的时候才会注入,as of 2.2.0 in favor of @ConditionalOnAvailableEndpoint
  @ConditionalOnAvailableEndpoint
  public DateTimeEndPoint dateTimeEndPoint() {
    return new DateTimeEndPoint();
  }

}
