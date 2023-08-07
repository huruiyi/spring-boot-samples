package com.example.test;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

  @Test
  void pageList(){
    IPage<Country> page = Page.of(0, 10);
    IPage<Country> pageList = countryService.page(page);
    List<Country> records = pageList.getRecords();
    records.forEach(System.out::println);
    Assertions.assertNotNull(records);
  }
}
