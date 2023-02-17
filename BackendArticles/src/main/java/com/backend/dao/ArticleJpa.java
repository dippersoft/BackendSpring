package com.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.Article;

public interface ArticleJpa extends JpaRepository<Article, Integer> {
	
	//@Query(value = "Select a from articles where a.id=?1",nativeQuery = true)
	//SELECT `_id`, `date`, title, content, image FROM api_rest_blog.articles;

	@Query(value = "SELECT `_id`, `date`, title, content, image FROM api_rest_blog.articles limit ?1",nativeQuery = true)
	List<Article> getLastArticles(int last);
}
