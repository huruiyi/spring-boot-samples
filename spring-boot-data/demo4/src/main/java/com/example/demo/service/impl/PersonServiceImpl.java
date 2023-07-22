package com.example.demo.service.impl;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;

  @Override
  @Transactional(rollbackFor = {IllegalArgumentException.class})
  public Person savePersonWithRollBack(Person person) {
    Person p = personRepository.save(person);

    if (StringUtils.equals(person.getName(), "fu")) {
      throw new IllegalArgumentException("已存在，数据将回滚");
    }
    return p;
  }

  @Override
  @Transactional(noRollbackFor = {IllegalArgumentException.class})
  public Person savePersonWithoutRollBack(Person person) {
    Person p = personRepository.save(person);
    if (StringUtils.equals(person.getName(), "fu")) {
      throw new IllegalArgumentException("已存在，数据将不会回滚");
    }
    return p;
  }

  @Override
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @Override
  public List<Person> findAll(Sort sort) {
    return personRepository.findAll(sort);
  }

  @Override
  public Page<Person> findAll(PageRequest pageRequest) {
    return personRepository.findAll(pageRequest);
  }

  @Override
  public List<Person> findByAddress(String address) {
    return personRepository.findByAddress(address);
  }

  @Override
  public Person findByNameAndAddress(String name, String address) {
    return personRepository.findByNameAndAddress(name, address);
  }

  @Override
  public Person findByNameStartsWith(String name) {
    return personRepository.findByNameStartsWith(name);
  }
}
