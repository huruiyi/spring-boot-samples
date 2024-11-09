package vip.fairy.service;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.fairy.properties.LogProperties;

@Aspect
public class LogAspect {

  private static final Log LOGGER = LogFactory.getLog(LogAspect.class);
  private LogProperties logProperties;

  public LogAspect(LogProperties logProperties) {
    this.logProperties = logProperties;
  }

  @Pointcut("@annotation(vip.fairy.ano.PrintLog)")
  public void Log() {

  }

  @Around("Log()")
  public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    String methodName = method.getName();
    LOGGER.info("-------------------------------------------------------------------");
    //打印调用url
    if (Boolean.TRUE.equals(logProperties.getPrintUrl())) {
      LOGGER.info("URL:" + request.getRequestURL().toString());
    }
    //打印ip
    if (Boolean.TRUE.equals(logProperties.getPrintIp())) {
      LOGGER.info("IP :" + request.getRemoteAddr());
    }
    //打印方法
    LOGGER.info("method :" + methodName);
    //打印参数
    LOGGER.info("parameter :" + Arrays.toString(joinPoint.getArgs()));
    Object result = joinPoint.proceed();
    //打印返回结果
    LOGGER.info("return :" + JSON.toJSONString(result));
    LOGGER.info("-------------------------------------------------------------------");
    return result;
  }
}
