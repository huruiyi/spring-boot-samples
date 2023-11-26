package com.example.service.unclassified;

import com.example.enums.Gender;
import com.example.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  public Person getUserInfo() {
    return new Person(1L, "ruiyi", "hu", 30, Gender.MALE);
  }
}
