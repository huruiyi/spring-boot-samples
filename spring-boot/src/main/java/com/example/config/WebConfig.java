package com.example.config;

import com.example.enums.HttpStatusCode;
import com.example.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.wildfly.common.annotation.NotNull;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


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
        boolean sslEnabled = env.getProperty("server.ssl.enabled", Boolean.class, env.getProperty("server.ssl.key-store") != null);
        if (sslEnabled && !request.isSecure()) {
          String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
          String httpsUrl = "https://" + request.getServerName() + ":443" + request.getRequestURI() + queryString;
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
      log.info("项目启动成功！地址端口(start port): {}://{}:{}", getProtocol(), inetAddress.getHostAddress(), port);

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
    protocol = getProtocol();
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

  private String getProtocol() {
    boolean sslEnabled = env.getProperty("server.ssl.enabled", Boolean.class, env.getProperty("server.ssl.key-store") != null);
    return sslEnabled ? "https" : "http";
  }

  @Bean
  //@ConditionalOnMissingBean     //当没有这个 bean 的时候才会创建
  //@ConditionalOnEnabledEndpoint //当开启 EndPoint 的时候才会注入,as of 2.2.0 in favor of @ConditionalOnAvailableEndpoint
  @ConditionalOnAvailableEndpoint
  public DateTimeEndPoint dateTimeEndPoint() {
    return new DateTimeEndPoint();
  }

  @Bean(name = "applicationEventMulticaster")
  public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
    SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

    eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
    return eventMulticaster;
  }

  /**
   * 启动通知（简版纯文本）：仅发送关键启动摘要。
   */
  @Bean
  public ApplicationRunner startupMailSender1(JavaMailSender javaMailSender, ApplicationContext ctx) {
    return args -> {
      Map<String, Object> info = buildStartupInfo(ctx);
      String text = String.format(
          "【启动通知·简版】%n"
              + "应用：%s%n"
              + "时间：%s%n"
              + "PID：%s%n"
              + "Profiles：%s%n"
              + "Local：%s%n"
              + "External：%s%n"
              + "Bean 数：%s%n",
          info.get("appName"),
          info.get("startTime"),
          info.get("pid"),
          info.get("profiles"),
          info.get("localUrl"),
          info.get("externalUrl"),
          info.get("beanCount"));

      javaMailSender.send(mimeMessage -> {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo("38761770@qq.com");
        helper.setFrom(mailFrom());
        helper.setSubject("【启动通知·简版】" + info.get("appName") + " 已启动");
        helper.setText(text, false);
      });
      log.info("已发送启动简版邮件，应用：{}", info.get("appName"));
    };
  }

  /**
   * 启动通知（详细 HTML）：使用 email-startup.html 模板展示完整启动信息。
   */
  @Bean
  public ApplicationRunner startupMailSender2(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine, ApplicationContext ctx) {
    return args -> {
      Map<String, Object> info = buildStartupInfo(ctx);
      Context context = new Context(LocaleContextHolder.getLocale());
      context.setVariables(info);
      String body = templateEngine.process("email-startup.html", context);

      javaMailSender.send(mimeMessage -> {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo("38761770@qq.com");
        helper.setFrom(mailFrom());
        helper.setSubject("【启动通知·详细】" + info.get("appName") + " 启动报告");
        helper.setText(body, true);
      });
      log.info("已发送启动详细 HTML 邮件，应用：{}", info.get("appName"));
    };
  }

  private String mailFrom() {
    return env.getProperty("spring.mail.username", "807776962@qq.com");
  }

  private Map<String, Object> buildStartupInfo(ApplicationContext ctx) {
    String processName = ManagementFactory.getRuntimeMXBean().getName();
    String pid = processName.contains("@") ? processName.split("@")[0] : processName;
    String protocol = getProtocol();
    String serverPort = env.getProperty("server.port", "8090");
    String contextPath = env.getProperty("server.servlet.context-path", "");
    if (contextPath.trim().isEmpty()) {
      contextPath = "/";
    }

    String hostAddress = "localhost";
    try {
      hostAddress = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      log.warn("无法获取主机地址: {}", e.getMessage());
    }

    String[] activeProfiles = env.getActiveProfiles();
    String profiles = activeProfiles.length == 0 ? "default" : String.join(",", activeProfiles);
    String[] beanNames = ctx.getBeanDefinitionNames();
    Arrays.sort(beanNames);

    Map<String, Object> info = new LinkedHashMap<>();
    info.put("appName", env.getProperty("spring.application.name", "application"));
    info.put("startTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    info.put("pid", pid);
    info.put("profiles", profiles);
    info.put("beanCount", beanNames.length);
    info.put("javaVersion", System.getProperty("java.version"));
    info.put("hostAddress", hostAddress);
    info.put("localUrl", protocol + "://localhost:" + serverPort + contextPath);
    info.put("externalUrl", protocol + "://" + hostAddress + ":" + serverPort + contextPath);
    return info;
  }

}