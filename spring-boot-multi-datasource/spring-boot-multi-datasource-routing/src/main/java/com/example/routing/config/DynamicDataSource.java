package com.example.routing.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 核心：动态数据源路由
 * determineCurrentLookupKey() 返回的数据源 key 决定本次用哪个库
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
