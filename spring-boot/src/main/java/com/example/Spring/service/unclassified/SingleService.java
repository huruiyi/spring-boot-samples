package com.example.Spring.service.unclassified;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SingleService implements ApplicationContextAware {

    public String sayHello() {
        return "Hello Spring Boot!";
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String[] getBeans() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        return beanDefinitionNames;
    }
}
