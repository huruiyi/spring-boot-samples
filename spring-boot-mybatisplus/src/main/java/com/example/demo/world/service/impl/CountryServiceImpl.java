package com.example.demo.world.service.impl;

import com.example.demo.world.entity.Country;
import com.example.demo.world.mapper.CountryMapper;
import com.example.demo.world.service.ICountryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fairy.vip
 * @since 2023-07-23
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements ICountryService {

}
