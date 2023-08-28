package com.example.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
      throws IOException, ServletException {
    Map<String, Object> resultMap = new HashMap<>();
    // 保存数据
    resultMap.put("code", "403");
    resultMap.put("msg", "无权访问");
    resultMap.put("data", null);

    // 设置返回消息类型
    response.setHeader("Content-type", "text/html;charset=UTF-8");
    response.setCharacterEncoding("utf-8");
    response.setContentType("application/json;charset=UTF-8");
    // 返回给请求端
    response.getWriter().write(resultMap.toString());
  }
}
