package com.example.mapper;

import com.example.model.Country;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CountryMapper {

  List<Country> selectAll();

  @Select("select * from country where id=#{0}")
  Country getById(Integer id);

}
