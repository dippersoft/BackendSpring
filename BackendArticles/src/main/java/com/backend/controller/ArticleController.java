package com.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.backend.model.Article;
import com.backend.service.ArticleService;

@RestController
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@GetMapping(value = "articles/{last}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Article> getArticles(@PathVariable ("last") int last) {
		System.out.println("LLEGA AL PERS");
		return articleService.getLastArticles(last);

	}
	
	@GetMapping(value = "articles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getArticles() {
		
		List<Article> result= articleService.getArticles();
		
		return ResponseEntity.status(HttpStatus.OK).body(result);

	}

	
//	@GetMapping(value = "articles", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<Article> getArticles() {
//		return articleService.getArticles();
//
//	}

	@PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Article saveArticle(@RequestBody Article article) {
		System.out.println("Soy el SAVE");
		Article article2 = new Article();
		// article2.setId(2222);
	    article2.setDate(new Date());
		article2.setTitle(article.getTitle());
		article2.setContent(article.getContent());
		article2.setImage(article.getImage());

		return articleService.saveArticle(article2);

	}

}
