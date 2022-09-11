package com.example.mapper;

import com.example.model.Country;
import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CountryMapper {
    List<Country> selectAll();

    @Select("select * from country where id=#{0}")
    Country getById(Integer id);

}