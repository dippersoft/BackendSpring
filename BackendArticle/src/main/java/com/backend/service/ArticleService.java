package com.backend.service;

import java.util.List;

import com.backend.model.Article;

public interface ArticleService {
	
	public List<Article> getArticles();
	
	public Article saveArticle(Article article);
	
	public List<Article> getLastArticles(int last);
	
	public Article getArticleSrv(int idArticle);
	
	public Article updateArticleSrv(int idArticle, Article article);
	
	public Article deleteArticleSrv(int idArticle);

	public List<Article> searchArticlesSrv(String search);
}
