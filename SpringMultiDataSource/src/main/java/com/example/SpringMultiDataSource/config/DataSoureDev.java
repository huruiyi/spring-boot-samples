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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.SpringMultiDataSource.memberDev.mapper", sqlSessionFactoryRef = "dataSourceDevSqlSessionFactory")
public class DataSoureDev {

    private final static String Dev_DataSourceBeanName = "dataSourceDev";
    private final static String Dev_SqlSessionFactory = "dataSourceDevSqlSessionFactory";
    private final static String Dev_DataSourceTransactionManager = "dataSourceDevTransactionManager";
    private final static String Dev_SqlSessionTemplate = "dataSourceDevSqlSessionTemplate";

    @Bean(Dev_DataSourceBeanName)
    @ConfigurationProperties(prefix = "dev.spring.datasource")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = Dev_SqlSessionFactory)
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier(Dev_DataSourceBeanName) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/memberDev/*.xml"));
        return bean.getObject();
    }

    @Bean(name = Dev_DataSourceTransactionManager)
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier(Dev_DataSourceBeanName) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = Dev_SqlSessionTemplate)
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier(Dev_SqlSessionFactory) SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
