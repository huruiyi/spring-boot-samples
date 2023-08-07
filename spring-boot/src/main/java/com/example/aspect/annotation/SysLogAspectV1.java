package com.example.aspect.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 系统日志切面处理，注解 + aop
 **/
@Aspect
@Component
@Slf4j
public class SysLogAspectV1 {

  /**
   * 切面入口， 扫描带SysLog注解的方法
   */
  @Pointcut("@annotation(com.example.annotation.SysLog)")
  public void logPointCut() {
  }

  @Around("logPointCut()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    long beginTime = System.currentTimeMillis();
    //执行方法
    Object result = point.proceed();
    Thread.sleep(3000);
    //执行时长(毫秒)
    long time = System.currentTimeMillis() - beginTime;
    log.info("方法环绕proceed，结果是 :" + result + "，耗时：" + time);

    return result;
  }
}
