package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dao.ArticleDao;
import com.backend.model.Article;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDao articleDao;
	
	@Override
	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	@Override
	public Article saveArticle(Article article) {
		return articleDao.saveArticle(article);
		
	}

	@Override
	public List<Article> getLastArticles(int last) {
		
		return articleDao.getLastArticles(last);
	}

}
