package com.example.routing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源总配置
 * 把所有 DataSource 注册到 DynamicDataSource 中
 */
@Configuration
public class DataSourceConfig {

    @Bean("masterDataSource")
    @ConfigurationProperties(prefix = "app.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slaveDataSource")
    @ConfigurationProperties(prefix = "app.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 路由数据源：包含 master + slave，默认走 master
     */
    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource());
        targetDataSources.put("slave", slaveDataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        return dynamicDataSource;
    }
}
