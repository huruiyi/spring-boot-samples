package com.example.config;

import com.example.filter.BrowseLogFilter;
import com.example.plugin.MyPlugin;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/static/**").addResourceLocations("/static");
    }

    @Bean
    public FilterRegistrationBean<BrowseLogFilter> myFilterBean() {
        FilterRegistrationBean<BrowseLogFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new BrowseLogFilter());
        filter.addUrlPatterns("/*");
        return filter;
    }

}