package com.example.springbootmybatisplus;


import com.example.springbootmybatisplus.dao.EpanEnterpriseDao;
import com.example.springbootmybatisplus.model.EpanEnterprise;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OracleTest {

    @Resource
    private EpanEnterpriseDao epanEnterpriseDao;


    @Test
    public void test2() {

    }
}
