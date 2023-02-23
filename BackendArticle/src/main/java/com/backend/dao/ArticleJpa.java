package com.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.Article;

public interface ArticleJpa extends JpaRepository<Article, Integer> {
	
	//@Query(value = "Select a from articles where a.id=?1",nativeQuery = true)
	//SELECT `_id`, `date`, title, content, image FROM api_rest_blog.articles;

	@Query(value = "SELECT `id`, `date`, title, content, image FROM API_REST_BLOG.Article limit ?1",nativeQuery = true)
	List<Article> getLastArticles(int last);
	
//	@Query(value = "SELECT `id`, `date`, title, content, image \n"
//			+ "FROM API_REST_BLOG.Article art\n"
//			+ "Where art.title like '%?1%' or art.content like '%?1%'",nativeQuery = true)
	@Query(value = "SELECT `id`, `date`, title, content, image FROM API_REST_BLOG.Article art where art.title like %?1% OR art.content like %?1%",nativeQuery = true)
    List<Article> searchArticlesJPA(String search);
}
