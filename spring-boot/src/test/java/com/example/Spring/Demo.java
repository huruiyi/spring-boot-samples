package com.example.Spring;

import org.junit.Test;

import java.util.Properties;

public class Demo {

  @Test
  public void test1() {
    Properties properties = System.getProperties();

    System.out.println(System.getenv("EmailPassword"));
  }
}
