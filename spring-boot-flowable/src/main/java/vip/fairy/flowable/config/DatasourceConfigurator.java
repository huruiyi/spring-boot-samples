package vip.fairy.flowable.config;

import javax.sql.DataSource;
import org.flowable.common.engine.impl.AbstractEngineConfiguration;
import org.flowable.common.engine.impl.EngineConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

@Component
public class DatasourceConfigurator implements EngineConfigurator {

  @Value("${flowable.datasource.url}")
  private String url;

  @Value("${flowable.datasource.type}")
  private Class<? extends DataSource> type;

  @Value("${flowable.datasource.driver-class-name}")
  private String driverClassName;

  @Value("${flowable.datasource.username}")
  private String username;

  @Value("${flowable.datasource.password}")
  private String password;


  @Override
  public void beforeInit(AbstractEngineConfiguration engineConfiguration) {
    DataSource dataSource = DataSourceBuilder.create()
        .type(type)
        .driverClassName(driverClassName)
        .url(url)
        .username(username)
        .password(password).build();
    engineConfiguration.setDataSource(dataSource);
  }

  @Override
  public void configure(AbstractEngineConfiguration engineConfiguration) {
  }

  @Override
  public int getPriority() {
    return 600000; // 保证该优先级最高
  }

}
