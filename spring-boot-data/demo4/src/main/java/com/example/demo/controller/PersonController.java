package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * http://localhost:7001/
     */
    @RequestMapping("/")
    public List<Person> home() {
        return personService.findAll();
    }


    /**
     * 测试findByAddress
     * http://localhost:7001/q1?address=%E5%8D%97%E4%BA%AC
     */
    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        return personService.findByAddress(address);
    }

    /**
     * 测试findByNameAndAddress
     * http://localhost:7001/q1?address=%E5%8D%97%E4%BA%AC&name=zz
     */
    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        return personService.findByNameAndAddress(name, address);
    }


    /**
     * 测试排序
     * http://localhost:7001/sort
     */
    @RequestMapping("/sort")
    public List<Person> sort() {
        return personService.findAll(Sort.by(Direction.ASC, "age"));
    }

    /**
     * 测试分页
     * http://localhost:7001/page
     */
    @RequestMapping("/page")
    public Page<Person> page() {
        return personService.findAll(PageRequest.of(1, 2));
    }


    /**
     * 测试分页
     * http://localhost:7001/findByNameStartsWith
     */
    @RequestMapping("/findByNameStartsWith")
    public Person person() {
        return personService.findByNameStartsWith("x");
    }


    /**
     * http://localhost:7001/rollback
     */
    @RequestMapping("/rollback")
    public Person rollback(Person person) {
        person.setName("fu");
        return personService.savePersonWithRollBack(person);
    }

    /**
     * http://localhost:7001/norollback
     */
    @RequestMapping("/norollback")
    public Person noRollback(Person person) {
        person.setName("fu");
        return personService.savePersonWithoutRollBack(person);
    }
}
