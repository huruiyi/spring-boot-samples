package com.example.service;

import com.example.entity.Country;

import java.util.List;

public interface ICountryService {

  Country save(Country country);

  List<Country> findAll();

  Country findById(Integer id);

  List<Country> delete(Integer id);
}
