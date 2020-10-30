package com.example.Spring.aspect;

import com.example.Spring.annotation.MyTestAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class TestAnnotationAspect {

    //拦截被TestAnnotation注解的方法；如果需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)
    @Pointcut("@annotation(com.example.Spring.annotation.MyTestAnnotation)")
    public void myAnnotationPointCut() {

    }

    @Before("myAnnotationPointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();

        Object[] args = joinPoint.getArgs();

        int parameterCount = method.getParameterCount();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameterCount; i++) {
            Parameter parameter = parameters[i];
            RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
            if (requestParam != null) {
                System.out.println("参数名：" + requestParam.name() + "，默认值：" + requestParam.defaultValue());
            }
            System.out.println(parameter.getName() + " = " + args[i]);
        }
        MyTestAnnotation annotation = method.getAnnotation(MyTestAnnotation.class);
        System.out.print("打印TestAnnotation 参数：" + annotation.value());
    }
}
