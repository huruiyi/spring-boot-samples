package com.enjoylearning.mybatis.basic;

import com.enjoylearning.mybatis.basic.entity.TUser;
import com.enjoylearning.mybatis.basic.entity.TUserExample;
import com.enjoylearning.mybatis.basic.entity.TUserExample.Criteria;
import com.enjoylearning.mybatis.basic.mapper.TUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Demo2 {

    private SqlSessionFactory sqlSessionFactory;


    @Before
    public void init() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 1.读取mybatis配置文件创SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }


    @Test
    public void testQueryExample() {
        // 2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取对应mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        // 4.执行查询语句并返回结果
        TUserExample example = new TUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmailLike("%163.com");
        criteria.andSexEqualTo(( byte ) 2);

        List<TUser> selectByExample = mapper.selectByExample(example);
        System.out.println(selectByExample);
    }
}
