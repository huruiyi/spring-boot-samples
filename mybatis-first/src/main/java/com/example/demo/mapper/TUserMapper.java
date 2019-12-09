package com.example.demo.mapper;


import com.example.demo.model.TUser;

public interface TUserMapper {

    TUser selectByPrimaryKey(Integer id);

}