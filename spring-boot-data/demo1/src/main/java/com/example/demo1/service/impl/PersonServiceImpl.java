package com.example.demo1.service.impl;

import com.example.demo1.model.Person;
import com.example.demo1.repository.PersonRepository;
import com.example.demo1.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

        if (StringUtils.equals(person.getName(), "fun")) {
            throw new IllegalArgumentException("已存在，数据将回滚");
        }
        return p;
    }

    @Override
    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if (StringUtils.equals(person.getName(), "fun")) {
            throw new IllegalArgumentException("已存在，数据将不会回滚");
        }
        return p;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person findByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> withQueryFindByAge(Integer age) {
        return personRepository.withQueryFindByAge(age);
    }
}
