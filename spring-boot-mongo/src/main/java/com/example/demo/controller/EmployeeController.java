package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/api/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    private EmployeeService service1;

    private EmployeeService service2;

    public EmployeeController(@Qualifier("v1") EmployeeService service1,
                              @Qualifier("v2") EmployeeService service2) {
        this.service1 = service1;
        this.service2 = service2;
    }

    /**
     * @Autowired
     * @Qualifier("v1") EmployeeService service1;
     * @Autowired
     * @Qualifier("v2") private EmployeeService service2;
     */


    @SneakyThrows
    @RequestMapping(value = "/v1/employee", method = RequestMethod.GET)
    public Iterable<Employee> employee1() {
        Iterable<Employee> employees = service1.findAll();
        service1.remove(employees);

        for (int i = 0; i < 10; i++) {
            service1.save(new Employee("V2-" + (UUID.randomUUID().toString().substring(1, 10) + "-" + i).replace("-", ""), 96, "胡大哥"));
        }

        return service1.findAll();
    }

    @SneakyThrows
    @RequestMapping(value = "/v2/employee", method = RequestMethod.GET)
    public Iterable<Employee> employee2() {
        for (int i = 0; i < 10; i++) {
            service2.save(new Employee("V1-" + (UUID.randomUUID().toString().substring(1, 10) + "-" + i).replace("-", ""), 96, "胡大哥"));
        }
        return service2.findAll();
    }
}
