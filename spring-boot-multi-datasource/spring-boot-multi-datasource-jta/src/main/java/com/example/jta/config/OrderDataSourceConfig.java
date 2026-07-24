package com.example.jta.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源1：订单库（XA 数据源）
 * 使用 AtomikosDataSourceBean 包装，支持 2PC 分布式事务
 */
@Configuration
@MapperScan(basePackages = "com.example.jta.order", sqlSessionFactoryRef = "orderSqlSessionFactory")
public class OrderDataSourceConfig {

    @Bean("orderDataSource")
    @Primary
    public DataSource orderDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("orderDS");
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        Properties props = new Properties();
        props.setProperty("url", "jdbc:mysql://127.0.0.1:3306/db_order?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        ds.setXaProperties(props);
        ds.setPoolSize(5);
        return ds;
    }

    @Bean("orderSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("orderDataSource") DataSource ds) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        return bean.getObject();
    }
}
