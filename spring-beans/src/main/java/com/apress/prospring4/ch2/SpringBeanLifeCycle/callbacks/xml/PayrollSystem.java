package com.apress.prospring4.ch2.SpringBeanLifeCycle.callbacks.xml;

/**
 * @author RaviKantSoni
 */
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PayrollSystem {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanLifeCycle.xml");

		EmployeeService employeeService = (EmployeeService) context.getBean("newEmployeeServiceBean");

		System.out.println(employeeService.generateEployeeId());

		context.close();

	}

}
