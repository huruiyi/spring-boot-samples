package com.example.demo.dao.impl;


import com.example.demo.dao.ArticleDao;
import com.example.demo.entity.Article;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ArticleDaoImpl implements ArticleDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveArticle(Article demoEntity) {
        mongoTemplate.save(demoEntity);
    }

    @Override
    public void removeArticle(String id) {
        mongoTemplate.remove(id);
    }

    @Override
    public void updateArticle(Article demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();
        update.set("title", demoEntity.getTitle());
        update.set("description", demoEntity.getDescription());
        update.set("by", demoEntity.getBy());
        update.set("url", demoEntity.getUrl());

        mongoTemplate.updateFirst(query, update, Article.class);
    }

    @Override
    public Article findArticleById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        Article demoEntity = mongoTemplate.findOne(query, Article.class);
        return demoEntity;
    }

    @Override
    public List<Article> list() {
        List<Article> articles = mongoTemplate.findAll(Article.class);
        return articles;
    }
}
