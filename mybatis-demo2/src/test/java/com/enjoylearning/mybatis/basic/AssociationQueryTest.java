package com.enjoylearning.mybatis.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.enjoylearning.mybatis.basic.entity.TUser;
import com.enjoylearning.mybatis.basic.mapper.TUserMapper;

public class AssociationQueryTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		inputStream.close();
	}

	@Test
	// 1对1两种关联方式
	public void testOneToOne() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
		System.out.println("*******************************************************");
		List<TUser> list1 = mapper.selectUserPosition1();
		for (TUser tUser : list1) {
			System.out.println(tUser);
		}
		System.out.println("*******************************************************");
		List<TUser> list2 = mapper.selectUserPosition2();
		for (TUser tUser : list2) {
			System.out.println(tUser);
		}
		System.out.println("*******************************************************");
	}

	@Test
	// 1对多两种关联方式
	public void testOneToMany() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
		List<TUser> selectUserJobs1 = mapper.selectUserJobs1();
		List<TUser> selectUserJobs2 = mapper.selectUserJobs2();
		System.out.println("*******************************************************");
		for (TUser tUser : selectUserJobs1) {
			System.out.println(tUser);
		}
		System.out.println("*******************************************************");
		for (TUser tUser : selectUserJobs2) {
			System.out.println(tUser.getJobs().size());
		}
		System.out.println("*******************************************************");
	}

	@Test
	public void testDiscriminator() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
		List<TUser> list = mapper.selectUserHealthReport();
		System.out.println("*******************************************************");
		for (TUser tUser : list) {
			System.out.println(tUser);
		}
		System.out.println("*******************************************************");
	}

	@Test
	// 多对多
	public void testManyToMany() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
		List<TUser> list = mapper.selectUserRole();
		System.out.println("*******************************************************");
		for (TUser tUser : list) {
			System.out.println(tUser.getRoles().size());
		}
		System.out.println("*******************************************************");
	}
}
