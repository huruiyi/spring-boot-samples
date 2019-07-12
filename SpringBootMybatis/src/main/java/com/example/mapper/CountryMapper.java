package com.example.mapper;

import com.example.Model.Country;

import java.util.List;

public interface CountryMapper {
    List<Country> selectAll();
}