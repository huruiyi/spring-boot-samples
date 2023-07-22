package com.example.demo;


import com.example.demo.dao.ArticleDao;
import com.example.demo.entity.Article;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.MongoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleTest {

  @Autowired
  private ArticleDao articleDao;

  @Test
  public void saveDemoTest() {
    Article demoEntity = new Article();
    demoEntity.setId(MongoUtils.getId());
    demoEntity.setTitle("Spring Boot 中使用 MongoDB");
    demoEntity.setDescription("fairy.vip - spring-samples-spring-boot-mongo");
    demoEntity.setBy("huruiyi");
    demoEntity.setUrl("http://fairy.vip/");
    articleDao.saveArticle(demoEntity);

    demoEntity = new Article();
    demoEntity.setId(MongoUtils.getId());
    demoEntity.setTitle("Spring Boot 中使用 MongoDB");
    demoEntity.setDescription("fairy.vip - spring-samples-spring-boot-mongo");
    demoEntity.setBy("huruiyi");
    demoEntity.setUrl("http://fairy.vip/");
    articleDao.saveArticle(demoEntity);
  }


  @Test
  public void updateDemoTest() {
    Article demoEntity = new Article();
    demoEntity.setId(MongoUtils.getId());
    demoEntity.setTitle("Spring Boot 中使用 MongoDB 更新数据");
    demoEntity.setDescription("fairy.vip - spring-samples-spring-boot-mongo");
    demoEntity.setBy("huruiyi");
    demoEntity.setUrl("http://fairy.vip/");
    articleDao.updateArticle(demoEntity);
  }

  @Test
  public void findDemoByIdTest() {
    Article article = articleDao.findArticleById("2bffb9271f1f49eda6cbbb4dbc4f9579");
    System.out.println(JSONObject.toJSONString(article));
  }

}
