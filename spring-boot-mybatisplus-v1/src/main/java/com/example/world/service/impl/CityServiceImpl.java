package com.example.world.service.impl;

import com.example.world.entity.City;
import com.example.world.mapper.CityMapper;
import com.example.world.service.ICityService;
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
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

}
