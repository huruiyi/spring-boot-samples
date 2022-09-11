package com.example.service.impl;


import com.example.mapper.CountryMapper;
import com.example.model.Country;
import com.example.model.User;
import com.example.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryMapper mapper;

    @Override
    public Country getById(Integer id) {
        return mapper.getById(id);
    }
}
