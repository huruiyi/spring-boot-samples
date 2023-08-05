package com.example.Spring;

import java.util.Properties;
import org.junit.Test;

public class Demo {

  @Test
  public void test1() {
    Properties properties = System.getProperties();

    System.out.println(System.getenv("EmailPassword"));
  }
}
