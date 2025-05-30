package com.example.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

/**
 * 启动开关，设置为true
 * management.endpoint.datetime.enabled=true
 * <p>
 * 动态修改
 * <p>
 * curl --location 'http://192.168.0.110:8090/actuator/datetime' \
 * --header 'Content-Type: application/json' \
 * --data '{
 *   "format": "日期：yyyy-MM-dd 时间：HH:mm:ss"
 * }'
 */
@Endpoint(id = "datetime")
public class DateTimeEndPoint {

  private String format = "yyyy-MM-dd HH:mm:ss";

  @ReadOperation
  public Map<String, Object> info() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("时间", new SimpleDateFormat(format).format(new Date()));
    return map;
  }

  @WriteOperation
  public void setDateTime(String format) {
    this.format = format;
  }

}
