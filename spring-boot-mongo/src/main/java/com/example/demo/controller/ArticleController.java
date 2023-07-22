package com.example.demo.controller;


import com.example.demo.dao.ArticleDao;
import com.example.demo.entity.Article;
import com.example.demo.utils.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  ArticleDao articleDao;

  @RequestMapping("/add")
  public String add(String name) {
    try {
      Article demoEntity = new Article();
      demoEntity.setId(MongoUtils.getId());
      demoEntity.setTitle(name);
      demoEntity.setDescription(name);
      demoEntity.setUrl("www.baidu.com");
      demoEntity.setBy(name);
      articleDao.saveArticle(demoEntity);
    } catch (Exception ex) {
      return ex.getMessage();
    }
    return "success";
  }

  @RequestMapping("list")
  public List<Article> list() {
    return articleDao.list();
  }

  @RequestMapping("get")
  public Article get(String id) {
    return articleDao.findArticleById(id);
  }

}
