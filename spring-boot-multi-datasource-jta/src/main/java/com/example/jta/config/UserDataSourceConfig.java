package com.example.jta.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源2：用户库（XA 数据源）
 */
@Configuration
@MapperScan(basePackages = "com.example.jta.user", sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSourceConfig {

    @Bean("userDataSource")
    public DataSource userDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("userDS");
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        Properties props = new Properties();
        props.setProperty("url", "jdbc:mysql://127.0.0.1:3306/db_user?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        ds.setXaProperties(props);
        ds.setPoolSize(5);
        return ds;
    }

    @Bean("userSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("userDataSource") DataSource ds) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        return bean.getObject();
    }
}
