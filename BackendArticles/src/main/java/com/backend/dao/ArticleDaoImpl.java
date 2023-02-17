package com.backend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.model.Article;

@Repository
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	ArticleJpa articleJpa;

	@Override
	public List<Article> getArticles() {

		return articleJpa.findAll();

	}

	@Override
	public Article saveArticle(Article article) {
	
		articleJpa.save(article);
		
		return article;
	}

	@Override
	public List<Article> getLastArticles(int last) {
		
		return articleJpa.getLastArticles(last);
	}

}
