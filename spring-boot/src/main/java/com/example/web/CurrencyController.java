package com.example.web;

import com.example.service.unclassified.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

  @Value("${currency-service.url}")
  private String url;

  @Value("${currency-service.username}")
  private String username;

  @Value("${currency-service.key}")
  private String key;


  private final CurrencyService currencyService;

  public CurrencyController(CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  @RequestMapping("/currency-configuration")
  public String currencyService() {
    return currencyService.toString();
  }
}
