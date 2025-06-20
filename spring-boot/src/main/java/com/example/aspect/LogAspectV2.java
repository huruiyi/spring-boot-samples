package com.example.aspect;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 日志切面
 */
@Aspect
@Component
@Slf4j
public class LogAspectV2 {

  @Pointcut("execution(public * com.example.web.*.*(..))")
  public void webLog() {
  }

  @Before("webLog()")
  public void deBefore(JoinPoint joinPoint) {
    // 接收到请求，记录请求内容
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 记录下请求内容
    log.info("URL : {}", request.getRequestURL().toString());
    log.info("HTTP_METHOD : {}", request.getMethod());
    log.info("IP : {}", request.getRemoteAddr());
    log.info("CLASS_METHOD : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    log.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(returning = "ret", pointcut = "webLog()")
  public void doAfterReturning(Object ret) {
    log.info("方法的返回值 : {}", ret);
  }

  //后置异常通知
  @AfterThrowing("webLog()")
  public void thrown(JoinPoint jp) {
    log.info("方法异常时执行.....");
  }

  //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
  @After("webLog()")
  public void after(JoinPoint jp) {
    log.info("方法最后执行.....");
  }

  //环绕通知,环绕增强，相当于MethodInterceptor
  @Around("webLog()")
  public Object around(ProceedingJoinPoint pjp) {
    log.info("方法环绕start.....");
    try {
      Object o = pjp.proceed();
      log.info("方法环绕proceed，结果是 :{}", o);
      return o;
    } catch (Throwable e) {
      e.printStackTrace();
      return null;
    }
  }
}
