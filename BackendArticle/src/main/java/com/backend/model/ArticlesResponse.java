package com.backend.model;

import java.util.List;

public class ArticlesResponse {

	String status;
	List<Article> articles;

	public ArticlesResponse(String status, List<Article> articles) {
		super();
		this.status = status;
		this.articles = articles;
	}

	public ArticlesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
