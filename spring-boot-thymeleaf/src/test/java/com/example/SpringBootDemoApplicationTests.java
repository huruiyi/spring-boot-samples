package com.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

  public class SpringBootDemoApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println( StringUtils.removeEnd("111.0",".0"));
		System.out.println(Integer.parseInt("1"));
	}

}
