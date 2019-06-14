package com.jdbc.dao;


import com.jdbc.model.Employee;

public interface EmployeeDao {

	void createEmployee();

	int getEmployeeCount();

	int insertEmployee(Employee employee);

	int deleteEmployeeById(int empId);

	Employee getEmployeeById(int empId);

}
