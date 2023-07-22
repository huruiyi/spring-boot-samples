package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  Iterable<Employee> findAll();

  Employee save(Employee employee) throws JsonProcessingException;

  Optional<Employee> findByID(String id);

  void remove(String id);

  void remove(Iterable<Employee> list);
}
