package com.example.plugin;

import java.sql.Connection;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Slf4j
@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {

  Properties properties = null;

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    log.info("MyPlugin.intercept");
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    log.info("MyPlugin.plugin");
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
    log.info("MyPlugin.setProperties");
    this.properties = properties;
  }
}
