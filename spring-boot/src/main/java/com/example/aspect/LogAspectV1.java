package com.example.aspect;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * * 使用@Before在切入点开始处切入内容 * 使用@After在切入点结尾处切入内容 * 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理） * 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容 *
 * 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 */
@Aspect
@Component
public class LogAspectV1 {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  //线程局部的变量,解决多线程中相同变量的访问冲突问题。
  ThreadLocal<Long> startTime = new ThreadLocal<>();

  //定义切点
  @Pointcut("execution(public * com.example..*.*(..))")
  public void aopWebLog() {
  }

  @Before("aopWebLog()")
  public void doBefore(JoinPoint joinPoint) {
    startTime.set(System.currentTimeMillis());
    // 接收到请求，记录请求内容
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attributes == null) {
      return;
    }
    HttpServletRequest request = attributes.getRequest();
    logger.info("\r\n");
    logger.info("URL : {}", request.getRequestURL().toString());
    logger.info("HTTP方法 : {}", request.getMethod());
    logger.info("IP地址 : {}", request.getRemoteAddr());
    logger.info("类的方法 : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    logger.info("参数 : {}", Arrays.toString(joinPoint.getArgs()));
    logger.info("参数 : {}", request.getQueryString());
    logger.info("\r\n");
  }

  @AfterReturning(pointcut = "aopWebLog()", returning = "retObject")
  public void doAfterReturning(Object retObject) {
    // 处理完请求，返回内容
    logger.info("\r\n");
    logger.info("应答值 : {}", retObject);
    logger.info("费时: {}", System.currentTimeMillis() - startTime.get());
    logger.info("\r\n");
  }

  //抛出异常后通知（After throwing advice） ： 在方法抛出异常退出时执行的通知。
  @AfterThrowing(pointcut = "aopWebLog()", throwing = "ex")
  public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
    logger.info("\r\n");
    logger.error("执行 " + " 异常", ex);
    logger.info("\r\n");
  }

}
