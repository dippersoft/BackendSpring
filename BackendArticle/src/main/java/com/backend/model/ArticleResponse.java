package com.backend.model;

public class ArticleResponse {

	String status;
	String message;
	Article article;

	public ArticleResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleResponse(String status, String message, Article article) {
		super();
		this.status = status;
		this.message = message;
		this.article = article;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
