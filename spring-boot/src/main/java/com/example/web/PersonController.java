package com.example.web;

import com.example.enums.Gender;
import com.example.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  private final List<Person> persons = new ArrayList<>();

  public PersonController() {
    Person persons1 = new Person(1L, "ruiyi", "hu", 30, Gender.MALE);
    Person persons2 = new Person(2L, "ruiyi", "hu", 31, Gender.MALE);
    Person persons3 = new Person(3L, "ruiyi", "hu", 32, Gender.MALE);
    Person persons4 = new Person(4L, "ruiyi", "hu", 33, Gender.MALE);
    persons.add(persons1);
    persons.add(persons2);
    persons.add(persons3);
    persons.add(persons4);
  }

  @GetMapping
  public List<Person> findAll() {
    return persons;
  }

  /**
   * <a href="http://localhost:9000/person/1">...</a>
   */
  @GetMapping("/{id}")
  public Person findById(@PathVariable Long id) {
    Optional<Person> optionalPerson = persons.stream().findFirst().filter(it -> it.getId().equals(id));
    return optionalPerson.orElse(null);
  }

  @PostMapping
  public Person add(@RequestBody Person p) {
    p.setId((long) (persons.size() + 1));
    persons.add(p);
    return p;
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    List<Person> p = persons.stream().filter(it -> it.getId().equals(id)).collect(Collectors.toList());
    persons.removeAll(p);
  }

  @PutMapping
  public void update(@RequestBody Person p) {
    Optional<Person> optionalPerson = persons.stream().findFirst().filter(it -> it.getId().equals(p.getId()));
    Person person = optionalPerson.orElse(null);
    persons.set(persons.indexOf(person), p);
  }


}
