package com.example.SpringMultiDataSource.memberPro.mapper;

import com.example.SpringMultiDataSource.memberPro.model.UserPro;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperPro {

    List<UserPro> findAll();
}
