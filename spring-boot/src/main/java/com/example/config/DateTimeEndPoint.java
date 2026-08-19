package com.example.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.util.StringUtils;

/**
 * 自定义 Actuator 端点 {@code /actuator/datetime}。
 * <p>
 * 详细用法见模块 {@code README.md} 中「DateTimeEndPoint」章节。
 * <p>
 * PowerShell 正确示例（不要使用 {@code \"} 转义）：
 * <pre>
 * curl.exe -k -X POST "https://localhost/actuator/datetime" ^
 *   -H "Content-Type: application/json" ^
 *   -d "{\"format\":\"yyyy-MM-dd HH:mm:ss\"}"
 * </pre>
 * 上面是 cmd.exe 写法；PowerShell 请用：
 * <pre>
 * curl.exe -k -X POST "https://localhost/actuator/datetime" `
 *   -H "Content-Type: application/json" `
 *   -d '{"format":"yyyy-MM-dd HH:mm:ss"}'
 * </pre>
 */
@Endpoint(id = "datetime")
public class DateTimeEndPoint {

  private volatile DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private volatile String format = "yyyy-MM-dd HH:mm:ss";

  @ReadOperation
  public Map<String, Object> info() {
    Map<String, Object> map = new LinkedHashMap<>();
    map.put("format", format);
    map.put("time", formatter.format(LocalDateTime.now()));
    return map;
  }

  @WriteOperation
  public Map<String, Object> setDateTime(String format) {
    if (!StringUtils.hasText(format)) {
      throw new IllegalArgumentException("format 不能为空");
    }
    String pattern = format.trim();
    try {
      this.formatter = DateTimeFormatter.ofPattern(pattern);
      this.format = pattern;
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("非法时间格式: " + pattern, ex);
    }
    return info();
  }

}
