package com.example.mapper;

import com.example.model.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CountryMapper {
    List<Country> selectAll();
}