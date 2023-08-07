package com.example.repository;

import com.example.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


@RepositoryRestResource(path = "person")
public interface PersonRepository extends JpaRepository<Person, Long> {

  List<Person> findByAddress(String address);

  Person findByNameAndAddress(String name, String address);

  @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
  Person findByNameStartsWith(@Param("name") String name);
}
