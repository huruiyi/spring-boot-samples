package com.example.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id = "datetime")
public class DateTimeEndPoint {

  private String format = "yyyy-MM-dd HH:mm:ss";

  /**
   * http://localhost:9000/actuator/datetime 用来显示监控指标
   *
   * @return map
   */
  @ReadOperation
  public Map<String, Object> info() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("时间", new SimpleDateFormat(format).format(new Date()));
    return map;
  }

  /**
   * 动态更改监控指标:http://localhost:9000/actuator/datetime
   * Post:
   * {
   *     "format": "日期：yyyy-MM-dd 时间：HH:mm:ss)"
   * }
   */
  @WriteOperation
  public void setDateTime(String format) {
    this.format = format;
  }

}