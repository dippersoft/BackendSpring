package com.backend.dao;

import java.util.List;

import com.backend.model.Article;

public interface ArticleDao {
	
	public List<Article> getArticles();
	
	public Article saveArticle(Article article);
	
	public List<Article> getLastArticles(int last);
	
	public Article getArticle(int idArticle);
	
	public Article updateArticle(Article article);
	
	public Article deleteArticle(Article article);
	
	public List<Article> searchArticles(String search);

}
