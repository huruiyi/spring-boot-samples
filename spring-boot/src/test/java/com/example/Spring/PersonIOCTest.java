package com.example.Spring;

import com.example.Spring.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonIOCTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void testIoc() {
    Person person = (Person) applicationContext.getBean("configPerson");
    System.out.println(person.toString());
  }
}
