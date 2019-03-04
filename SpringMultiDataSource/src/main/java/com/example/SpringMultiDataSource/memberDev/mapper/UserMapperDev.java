package com.example.SpringMultiDataSource.memberDev.mapper;

import com.example.SpringMultiDataSource.memberDev.model.UserDev;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperDev {

    List<UserDev> findAll();
}
