package com.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.pro.mapper", sqlSessionFactoryRef = "dataSource_Pro_SqlSessionFactory")
public class DataSourcePro {


  @Bean("dataSource_Pro")
  @ConfigurationProperties(prefix = "pro.spring.datasource")
  public DataSource testDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "dataSource_Pro_SqlSessionFactory")
  public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSource_Pro") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/memberPro/*.xml"));
    return bean.getObject();
  }

  @Bean(name = "dataSource_Pro_TransactionManager")
  public DataSourceTransactionManager testTransactionManager(@Qualifier("dataSource_Pro") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "dataSource_Pro_SqlSessionTemplate")
  public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("dataSource_Pro_SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
