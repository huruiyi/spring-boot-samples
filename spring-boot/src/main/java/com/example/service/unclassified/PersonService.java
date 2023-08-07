package com.example.service.unclassified;

import com.example.model.Person;
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
