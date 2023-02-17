package com.backend.service;

import java.util.List;

import com.backend.model.Article;

public interface ArticleService {
	
	public List<Article> getArticles();
	
	public Article saveArticle(Article article);
	
	public List<Article> getLastArticles(int last);

}
