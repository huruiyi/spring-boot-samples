package com.example;

import java.util.Properties;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DemoTest {

  @Test
  public void getEmailTest() {
    Properties properties = System.getProperties();
    Assertions.assertNotNull(properties);
    String emailPassword = System.getenv("EmailPassword");
    Assertions.assertNotNull(emailPassword);
  }

}
