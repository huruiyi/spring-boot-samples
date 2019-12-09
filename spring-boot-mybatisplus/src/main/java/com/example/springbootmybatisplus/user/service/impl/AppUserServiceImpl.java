package com.example.springbootmybatisplus.user.service.impl;

import com.example.springbootmybatisplus.user.entity.AppUser;
import com.example.springbootmybatisplus.user.mapper.AppUserMapper;
import com.example.springbootmybatisplus.user.service.IAppUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-03-20
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

}
