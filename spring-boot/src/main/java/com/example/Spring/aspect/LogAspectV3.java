package com.example.Spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * aop 记录所有web请求和响应信息
 */
@Aspect
@Component
@Slf4j
public class LogAspectV3 {

  @Pointcut("execution(public * com.example.Spring.web.*.*(..))")
  public void webLog() {
  }

  /**
   * 使用aop前置通知，拦截请求参数信息，写文件或写数据库，分布式日志收集系统
   */
  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    log.info("url: " + request.getRequestURL());
    log.info("http_method: " + request.getMethod());
    log.info("ip: " + request.getRemoteAddr());
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String parameterName = parameterNames.nextElement();
      log.info("parameterName:{}, value:{}", parameterName, request.getParameter(parameterName));
    }
  }


  /**
   * 后置通知
   */
  @AfterReturning(returning = "response", pointcut = "webLog()")
  public void doAfterReturn(Object response) {
    log.info("response: " + response);
  }

}
