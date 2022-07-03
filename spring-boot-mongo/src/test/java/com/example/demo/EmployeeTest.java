package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeTest {

    @Autowired
    @Qualifier("v1")
    EmployeeService service1;

    @Test
    void findAll() {
        Iterable<Employee> employees = service1.findAll();
        assertNotNull(employees);
        System.out.println(employees);
    }

}
