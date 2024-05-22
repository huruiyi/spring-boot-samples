package com.example.service.impl;


import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "currency-service")
public class CurrencyService {
  private String url;
  private String username;
  private String key;
}
