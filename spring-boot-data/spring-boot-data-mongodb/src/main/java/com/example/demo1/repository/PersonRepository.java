package com.example.demo1.repository;

import com.example.demo1.model.Person;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author yangyueming
 */

@RepositoryRestResource
public interface PersonRepository extends MongoRepository<Person, String> {

  Person findByName(String name);

  @Query("{'age': ?0}")
  List<Person> withQueryFindByAge(Integer age);

}
