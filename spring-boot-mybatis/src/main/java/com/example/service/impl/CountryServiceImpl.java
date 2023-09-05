package com.example.service.impl;


import com.example.mapper.CountryMapper;
import com.example.model.Country;
import com.example.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

  private final CountryMapper mapper;

  public CountryServiceImpl(CountryMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public Country getById(Integer id) {
    return mapper.getById(id);
  }
}
