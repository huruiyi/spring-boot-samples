package com.apress.prospring4.ch2.BeanDefinitionTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("BeanDefinitionTemplate.xml");
        // using 'employeeBean'
        Employee employeeA = (Employee) context.getBean("employeeBean");
        System.out.println(employeeA);

        // using 'indianEmployee'
        Employee employeeB = (Employee) context.getBean("indianEmployee");
        System.out.println(employeeB);

    }

}
