package com.example.demo.mapper;

import com.example.demo.model.Country;

import java.util.List;

public interface CountryMapper {

  List<Country> selectAll();
}