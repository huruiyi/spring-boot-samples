package com.example.web;

import com.example.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

  private List<Person> persons = new ArrayList<>();

  @GetMapping
  public List<Person> findAll() {
    return persons;
  }

  @GetMapping("/{id}")
  public Person findById(@RequestParam("id") Long id) {
    return persons.stream().filter(it -> it.getId().equals(id)).findFirst().get();
  }

  @PostMapping
  public Person add(@RequestBody Person p) {
    p.setId((long) (persons.size() + 1));
    persons.add(p);
    return p;
  }

  @DeleteMapping("/{id}")
  public void delete(@RequestParam("id") Long id) {
    List<Person> p = persons.stream().filter(it -> it.getId().equals(id)).collect(Collectors.toList());
    persons.removeAll(p);
  }

  @PutMapping
  public void update(@RequestBody Person p) {
    Person person = persons.stream().filter(it -> it.getId().equals(p.getId())).findFirst().get();
    persons.set(persons.indexOf(person), p);
  }

}
