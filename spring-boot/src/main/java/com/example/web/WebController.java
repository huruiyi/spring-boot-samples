package com.example.web;

import com.example.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

  @Autowired
  private BusinessService businessService;

  public long returnValueFromBusinessService() {
    return businessService.calculateSum();
  }
  @GetMapping("/sum")
  public long displaySum() {
    return businessService.calculateSum();
  }

}