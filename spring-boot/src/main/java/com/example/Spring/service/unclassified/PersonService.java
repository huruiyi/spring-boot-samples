package com.example.Spring.service.unclassified;

import com.example.Spring.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  public Person getUserInfo() {
    Person user = new Person();
    user.setFirstName("San");
    user.setLastName("Zhang");
    user.setAge(18);
    return user;
  }
}
