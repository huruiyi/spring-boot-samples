package com.jdbc;

import com.jdbc.dao.EmployeeDao;
import com.jdbc.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        EmployeeDao employeeDao = context.getBean("employeeDaoImp", EmployeeDao.class);

        // create employee table
        employeeDao.createEmployee();

        System.out.println(employeeDao.getEmployeeCount());

        Employee emp = new Employee(1, "James", 28);
        employeeDao.insertEmployee(emp);

        Employee employee = employeeDao.getEmployeeById(1);
        System.out.println(employee.getEmpId() + " - " + employee.getName());

        System.out.println(employeeDao.deleteEmployeeById(1));

    }
}
