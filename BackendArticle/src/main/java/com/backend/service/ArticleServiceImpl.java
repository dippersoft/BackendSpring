package com.backend.service;

import java.util.Date;
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

	@Override
	public Article getArticleSrv(int idArticle) {

		return articleDao.getArticle(idArticle);
	}

	@Override
	public Article updateArticleSrv(int idArticle, Article article) {
		if(articleDao.getArticle(idArticle)!=null) {
			System.out.println("Encuentra el articulo "+idArticle);
			article.setId(idArticle);
			article.setDate(new Date());
			return articleDao.updateArticle(article);
		}
		return null;
	}

	@Override
	public Article deleteArticleSrv(int idArticle) {
		Article article=articleDao.getArticle(idArticle);
		if(article!=null) {
			System.out.println("Se elimina el articulo "+idArticle);
			
			return articleDao.deleteArticle(article);
		}
		return null;
	}

	@Override
	public List<Article> searchArticlesSrv(String search) {
		
		return articleDao.searchArticles(search);
	}

	
}
