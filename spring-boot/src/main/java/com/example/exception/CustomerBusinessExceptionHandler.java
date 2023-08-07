package com.example.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class CustomerBusinessExceptionHandler {

  @ResponseBody
  @ExceptionHandler(BusinessException.class)
  public Map<String, Object> businessExceptionHandler(BusinessException e) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("code", e.getCode());
    map.put("message", e.getMessage());
    return map;
  }


  @ResponseBody
  @ExceptionHandler({ArithmeticException.class})
  public Map<String, Object> fix(Exception e) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", "自定义异常信息：" + e.getMessage());
    return map;
  }

  @ResponseBody
  @ExceptionHandler({NoHandlerFoundException.class})
  public Object error() {
    Map<String, String> map = new HashMap<>();
    map.put("error", "页面不存在-触发NoHandlerFoundException异常");
    return map;
  }

}