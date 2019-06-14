package com.example.JdbcHrPayrollSystem;

import com.example.JdbcHrPayrollSystem.dao.EmployeeDao;
import com.example.JdbcHrPayrollSystem.dao.EmployeeDaoImpl;
import com.example.JdbcHrPayrollSystem.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class JdbcHrPayrollSystemApplication {

    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        // create employee table
        employeeDao.createEmployee();
        // insert into employee table
        employeeDao.insertEmployee(new Employee(1, "Ravi"));
        // get employee based on id
        Employee employee = employeeDao.getEmployeeById(1);
        System.out.println("Employee name: " + employee.getName());
    }

}
