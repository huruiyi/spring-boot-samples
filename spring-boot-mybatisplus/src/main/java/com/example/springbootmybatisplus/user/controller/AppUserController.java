package com.example.springbootmybatisplus.user.controller;


import com.example.springbootmybatisplus.user.entity.AppUser;
import com.example.springbootmybatisplus.user.service.IAppUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-20
 */
@RestController
@RequestMapping("/user/app-user")
public class AppUserController {

    @Resource
    IAppUserService appUserService;

    @RequestMapping(value = "list")
    public List<AppUser> hello() {
        List<AppUser> list = appUserService.list();
        return list;
    }
}
