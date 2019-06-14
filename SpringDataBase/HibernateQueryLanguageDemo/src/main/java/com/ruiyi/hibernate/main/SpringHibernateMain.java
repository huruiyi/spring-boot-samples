package com.ruiyi.hibernate.main;

import com.ruiyi.hibernate.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ruiyi.hibernate.service.EmployeeService;

public class SpringHibernateMain {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");

		EmployeeService employeeService = context.getBean("employeeServiceImpl", EmployeeService.class);

		for (Employee emp : employeeService.hqlUsingPagination())
			System.out.println(emp);
	}
}
