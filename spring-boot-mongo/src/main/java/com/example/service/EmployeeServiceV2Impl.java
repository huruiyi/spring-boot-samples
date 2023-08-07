package com.example.service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service("v2")
public class EmployeeServiceV2Impl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public Iterable<Employee> findAll() {
    log.debug("v2.findAll()", EmployeeServiceV2Impl.class);
    return employeeRepository.findAll();
  }

  @Override
  public Employee save(Employee employee) {
    log.debug("v2.save(Employee employee)", EmployeeServiceV2Impl.class);
    return employeeRepository.save(employee);
  }

  @Override
  public Optional<Employee> findByID(String id) {
    log.debug("v2.findByID(String id)", EmployeeServiceV2Impl.class);
    return employeeRepository.findById(id);
  }

  @Override
  public void remove(String id) {
    log.debug("v2.remove(String id)", EmployeeServiceV2Impl.class);
    Employee st = new Employee();
    st.setId(id);
    employeeRepository.delete(st);
  }

  @Override
  public void remove(Iterable<Employee> list) {
    log.debug("v2.remove(List<Employee> list)", EmployeeServiceV2Impl.class);
    employeeRepository.deleteAll(list);
  }
}
