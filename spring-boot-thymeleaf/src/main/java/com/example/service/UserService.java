package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.bean.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	public User findOneUser() {
		User user = new User();
		user.setId(1);
		user.setUsername("许文强");
		user.setPassword("0001");
		user.setTelephone("13512368546");
		user.setRegisterTime(new Date());
		user.setPopedom(0);
		return user;
	}

	public List<User> findAllUsers() {
		List<User> users = new ArrayList<User>();

		User user = new User();
		user.setId(1);
		user.setUsername("丁力");
		user.setPassword("0002");
		user.setTelephone("13812567852");
		user.setRegisterTime(new Date());
		user.setPopedom(0);
		users.add(user);

		user = new User();
		user.setId(2);
		user.setUsername("冯程程");
		user.setPassword("0003");
		user.setTelephone("13689564785");
		user.setRegisterTime(new Date());
		user.setPopedom(1);
		users.add(user);

		user = new User();
		user.setId(3);
		user.setUsername("冯敬尧");
		user.setPassword("0004");
		user.setTelephone("13952126357");
		user.setRegisterTime(new Date());
		user.setPopedom(1);
		users.add(user);

		user = new User();
		user.setId(4);
		user.setUsername("方艳芸");
		user.setPassword("0005");
		user.setTelephone("13612356987");
		user.setRegisterTime(new Date());
		user.setPopedom(1);
		users.add(user);
		return users;
	}
}
