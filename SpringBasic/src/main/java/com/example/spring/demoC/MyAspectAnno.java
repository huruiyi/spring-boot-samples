package com.example.spring.demoC;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author hurui
 */
@Aspect
public class MyAspectAnno {

    /**
     * 前置通知
     */
    @Before(value = "execution(* com.example.spring.demoC.OrderDao.save(..))")
    public void before() {
        System.out.println("前置增强......");
    }

    /**
     * 后置通知
     *
     * @param result
     */
    @AfterReturning(value = "execution(* com.example.spring.demoC.OrderDao.delete(..))", returning = "result")
    public void afterReturning(Object result) {
        System.out.println("后置增强......" + result);
    }

    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(* com.example.spring.demoC.OrderDao.update(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before update......");
        Object proceed = joinPoint.proceed();
        System.out.println("after  update......");
        return proceed;
    }

    /**
     * 异常抛出通知
     *
     * @param errorMessage
     */
    @AfterThrowing(value = "com.example.spring.demoC.MyAspectAnno.pointCut1()", throwing = "errorMessage")
    public void afterThrowing(Throwable errorMessage) {
        System.out.println("异常抛出......" + errorMessage.getMessage());
    }

    /**
     * 最终通知
     */
    @After(value = "com.example.spring.demoC.MyAspectAnno.pointCut1()")
    public void after() {
        System.out.println("最终增强......");
    }

    /* AOP切入点配置 */
    @Pointcut(value = "execution(* com.example.spring.demoC.OrderDao.find(..))")
    public void pointCut1() {
    }

    @Pointcut(value = "execution(* com.example.spring.demoC.OrderDao.save(..))")
    public void pointCut2() {
    }

    @Pointcut(value = "execution(* com.example.spring.demoC.OrderDao.update(..))")
    public void pointCut3() {
    }

    @Pointcut(value = "execution(* com.example.spring.demoC.OrderDao.delete(..))")
    public void pointCut4() {
    }
}
