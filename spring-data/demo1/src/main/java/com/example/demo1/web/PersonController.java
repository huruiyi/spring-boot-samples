package com.example.demo1.web;

import com.example.demo1.model.Location;
import com.example.demo1.model.Person;
import com.example.demo1.repository.PersonRepository;
import com.example.demo1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
public class PersonController {


    @Autowired
    PersonService personService;


    /**
     * http://localhost:8080/save
     */
    @GetMapping("/save")
    public Person save() {
        Person person = new Person("wyf", 32);
        Set<Location> locationSet = new LinkedHashSet<>();
        Location location1 = new Location("上海", "2009");
        Location location2 = new Location("合肥", "2010");
        Location location3 = new Location("广州", "2011");
        Location location4 = new Location("马鞍山", "2012");
        locationSet.add(location1);
        locationSet.add(location2);
        locationSet.add(location3);
        locationSet.add(location4);
        person.setLocationSet(locationSet);

        return personService.save(person);
    }

    /**
     * http://localhost:8080/findByName?name=wyf
     */
    @GetMapping("/findByName")
    public Person findByName(@RequestParam String name) {
        return personService.findByName(name);
    }

    /**
     * http://localhost:8080/findAll
     */
    @GetMapping("/findAll")
    public List<Person> findAll() {
        return personService.findAll();
    }

    /**
     * http://localhost:8080/findByAge?age=32
     */
    @RequestMapping("/findByAge")
    public List<Person> findByAge(Integer age) {
        return personService.withQueryFindByAge(age);
    }




}
