package com.example.world.service.impl;

import com.example.world.entity.Country;
import com.example.world.mapper.CountryMapper;
import com.example.world.service.ICountryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huruiyi
 * @since 2023-07-23 08:50:02
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements ICountryService {

}
