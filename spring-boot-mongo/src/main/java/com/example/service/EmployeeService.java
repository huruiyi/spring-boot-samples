package com.example.service;

import com.example.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Optional;

public interface EmployeeService {

  Iterable<Employee> findAll();

  Employee save(Employee employee) throws JsonProcessingException;

  Optional<Employee> findByID(String id);

  void remove(String id);

  void remove(Iterable<Employee> list);
}
