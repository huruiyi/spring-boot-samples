package com.example.jdbc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 纯 JDBC 多数据源配置
 * 创建两个独立的 DataSource 和对应的 JdbcTemplate，
 * 使用时通过 @Qualifier 指定用哪个
 */
@Configuration
public class DataSourceConfig {

    // ==================== 订单库 ====================

    @Bean("orderDataSource")
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.order")
    public DataSource orderDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("orderJdbcTemplate")
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // ==================== 用户库 ====================

    @Bean("userDataSource")
    @ConfigurationProperties(prefix = "app.datasource.user")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("userJdbcTemplate")
    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
