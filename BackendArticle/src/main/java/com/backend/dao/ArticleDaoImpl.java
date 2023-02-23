package com.backend.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

	@Override
	public Article getArticle(int idArticle) {

		return articleJpa.findById(idArticle).orElse(null);
	}

	@Override
	public Article updateArticle(Article article) {
		System.out.println("llega al save");
		return articleJpa.save(article);
	}

	@Override
	public Article deleteArticle(Article article) {
		articleJpa.delete(article);
		return article;
	}

	@Override
	public List<Article> searchArticles(String search) {
		System.out.println("LLEGA al SEARCH");
		return articleJpa.searchArticlesJPA(search);
	}

}
