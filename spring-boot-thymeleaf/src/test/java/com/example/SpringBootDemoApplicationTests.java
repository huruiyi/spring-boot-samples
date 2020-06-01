package com.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

  public class SpringBootDemoApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println( StringUtils.removeEnd("111.0",".0"));
		System.out.println(Integer.parseInt("1"));
	}

}
