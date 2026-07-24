package com.example.routing.aspect;

import com.example.routing.annotation.TargetDataSource;
import com.example.routing.config.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * AOP 切面：拦截 @TargetDataSource 注解，设置 ThreadLocal
 * @Order(-1) 确保在 @Transactional 之前执行
 */
@Aspect
@Component
@Order(-1)
public class DataSourceAspect {

    @Around("@annotation(targetDataSource)")
    public Object around(ProceedingJoinPoint point, TargetDataSource targetDataSource) throws Throwable {
        String dsKey = targetDataSource.value();
        DataSourceContextHolder.setDataSource(dsKey);
        try {
            return point.proceed();
        } finally {
            DataSourceContextHolder.clear();  // 必须清理，避免内存泄漏
        }
    }
}
