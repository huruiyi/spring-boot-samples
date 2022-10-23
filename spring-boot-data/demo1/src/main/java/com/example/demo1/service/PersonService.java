package com.example.demo1.service;

import com.example.demo1.model.Person;

import java.util.List;

/**
 * @author yangyueming
 */
public interface PersonService {

    Person savePersonWithRollBack(Person person);

    Person savePersonWithoutRollBack(Person person) ;

    Person save(Person person);

    Person findByName(String name);

    List<Person> findAll();

    List<Person> withQueryFindByAge(Integer age);
}
