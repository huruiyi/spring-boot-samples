package com.example.demo.dao;

import com.example.demo.entity.Article;

import java.util.List;

public interface ArticleDao {

  void saveArticle(Article article);

  void removeArticle(String id);

  void updateArticle(Article article);

  Article findArticleById(String id);

  List<Article> list();
}
