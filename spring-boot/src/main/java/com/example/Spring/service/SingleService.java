package com.example.Spring.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SingleService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public String sayHello() {
        return "Hello Spring Boot!";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String[] getBeans() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        return beanDefinitionNames;
    }
}
