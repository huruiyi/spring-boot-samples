package com.example.service;


import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<User> getUsers() {
    return mongoTemplate.findAll(User.class, "users");
  }

  public User getUser(Integer id) {
    Query query = Query.query(Criteria.where("id").is(id));
    return mongoTemplate.findOne(query, User.class);
  }

  public void createUser(User user) {
    mongoTemplate.save(user, "users");
  }
}

