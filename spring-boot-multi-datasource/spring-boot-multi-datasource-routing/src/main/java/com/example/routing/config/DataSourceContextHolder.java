package com.example.routing.config;

/**
 * ThreadLocal 持有当前线程的数据源 key
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setDataSource(String ds) {
        CONTEXT.set(ds);
    }

    public static String getDataSource() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
