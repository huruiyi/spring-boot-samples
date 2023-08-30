package com.example.test;


import com.example.world.entity.Country;
import com.example.world.service.ICountryService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryTest {

  @Autowired
  ICountryService countryService;

  @Test
  void list(){
    List<Country> list = countryService.list();
    list.forEach(System.out::println);
    Assertions.assertNotNull(list);
  }
}
