package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonGeneratorImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("v1")
public class EmployeeServiceV1Impl implements EmployeeService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Iterable<Employee> findAll() {
        log.debug("v1.findAll()",EmployeeServiceV1Impl.class);
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) throws JsonProcessingException {
        log.debug("v1.save(Employee employee)：", OBJECT_MAPPER.writeValueAsString(employee));
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findByID(String id) {
        log.debug("v1.findByID(String id)",EmployeeServiceV1Impl.class);
        return employeeRepository.findById(id);
    }

    @Override
    public void remove(String id) {
        log.debug("v1.remove(String id)",EmployeeServiceV1Impl.class);
        Employee st = new Employee();
        st.setId(id);
        employeeRepository.delete(st);
    }

    @Override
    public void remove(Iterable<Employee> list) {
        log.debug("v1.remove(List<Employee> list)",EmployeeServiceV1Impl.class);
        employeeRepository.deleteAll(list);
    }
}
