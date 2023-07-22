package com.example.demo.service;

import com.example.demo.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author yangyueming
 */
public interface PersonService {

  Person savePersonWithRollBack(Person person);

  Person savePersonWithoutRollBack(Person person);

  List<Person> findAll();

  List<Person> findAll(Sort sort);

  Page<Person> findAll(PageRequest pageRequest);


  List<Person> findByAddress(String address);


  Person findByNameAndAddress(String name, String address);

  Person findByNameStartsWith(String name);
}
