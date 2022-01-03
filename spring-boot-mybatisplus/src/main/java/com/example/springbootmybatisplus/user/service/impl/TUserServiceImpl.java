package com.example.springbootmybatisplus.user.service.impl;

import com.example.springbootmybatisplus.user.entity.TUser;
import com.example.springbootmybatisplus.user.mapper.TUserMapper;
import com.example.springbootmybatisplus.user.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-01-03
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
