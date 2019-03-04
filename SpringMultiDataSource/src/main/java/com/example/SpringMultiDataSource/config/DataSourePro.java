package com.example.SpringMultiDataSource.config;

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
@MapperScan(basePackages = "com.example.SpringMultiDataSource.memberPro.mapper", sqlSessionFactoryRef = "dataSourceProSqlSessionFactory")
public class DataSourePro {

    private final static String Pro_DataSourceBeanName = "dataSourcePro";
    private final static String Pro_SqlSessionFactory = "dataSourceProSqlSessionFactory";
    private final static String Pro_DataSourceTransactionManager = "dataSourceProTransactionManager";
    private final static String Pro_SqlSessionTemplate = "dataSourceProSqlSessionTemplate";

    @Bean(Pro_DataSourceBeanName)
    @ConfigurationProperties(prefix = "pro.spring.datasource")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = Pro_SqlSessionFactory)
    public SqlSessionFactory testSqlSessionFactory(@Qualifier(Pro_DataSourceBeanName) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/memberPro/*.xml"));
        return bean.getObject();
    }

    @Bean(name = Pro_DataSourceTransactionManager)
    public DataSourceTransactionManager testTransactionManager(@Qualifier(Pro_DataSourceBeanName) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = Pro_SqlSessionTemplate)
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier(Pro_SqlSessionFactory) SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}