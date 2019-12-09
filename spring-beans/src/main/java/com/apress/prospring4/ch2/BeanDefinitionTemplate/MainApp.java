package com.apress.prospring4.ch2.BeanDefinitionTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("BeanDefinitionTemplate.xml");

        Employee employeeA = (Employee) context.getBean("employeeBean");
        System.out.println(employeeA);

        Employee employeeB = (Employee) context.getBean("indianEmployee");
        System.out.println(employeeB);

    }

}
