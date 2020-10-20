package com.example.config;

import com.example.filter.BrowseLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

//    @Bean
//    public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilter() {
//        FilterRegistrationBean<CharacterEncodingFilter> filter = new FilterRegistrationBean<>();
//        filter.setFilter(new CharacterEncodingFilter());
//        filter.addInitParameter("encoding", "UTF-8");
//        filter.addInitParameter("forceEncoding", "true");
//        filter.addUrlPatterns("/*");
//        return filter;
//    }
}