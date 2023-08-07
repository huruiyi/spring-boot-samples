package com.example;

import static org.hamcrest.CoreMatchers.is;

import com.example.model.Person;
import com.example.service.unclassified.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

  @Autowired
  private PersonService personService;

  @Test
  public void getUserInfo() {
    Person person = personService.getUserInfo();
    //比较实际的值和用户预期的值是否一样
    Assert.assertEquals(18, person.getAge());
    Assert.assertThat(person.getLastName(), is("Zhang"));

  }

}
