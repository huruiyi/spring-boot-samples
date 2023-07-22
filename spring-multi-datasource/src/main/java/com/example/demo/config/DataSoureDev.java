package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.demo.dev.mapper", sqlSessionFactoryRef = "dataSource_Dev_SqlSessionFactory")
public class DataSoureDev {


  @Bean("dataSource_Dev")
  @ConfigurationProperties(prefix = "dev.spring.datasource")
  @Primary
  public DataSource testDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "dataSource_Dev_SqlSessionFactory")
  @Primary
  public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSource_Dev") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/memberDev/*.xml"));
    return bean.getObject();
  }

  @Bean(name = "dataSource_Dev_TransactionManager")
  @Primary
  public DataSourceTransactionManager testTransactionManager(@Qualifier("dataSource_Dev") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "dataSource_Dev_SqlSessionTemplate")
  @Primary
  public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("dataSource_Dev_SqlSessionFactory") SqlSessionFactory sqlSessionFactory)
      throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
