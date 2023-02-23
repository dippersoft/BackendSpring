package com.backend.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.model.Article;
import com.backend.model.ArticleResponse;
import com.backend.model.ArticlesResponse;
import com.backend.service.ArticleService;
import com.backend.service.FileService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping(value = "api")
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@Autowired
	FileService fileService;

	@GetMapping(value = "/articles/{last}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getArticles(@PathVariable("last") int last) {
		System.out.println("LLEGA AL PERS" + last);

//		Article article= new Article();
//		article.setContent("Contenido metido");
//		article.setTitle("Title metido");
//		article.setImage("Image metida");
//		
//		List<Article> result= new ArrayList<>();
//		result.add(article);
		List<Article> result = articleService.getLastArticles(last);
		return ResponseEntity.status(HttpStatus.OK).body(new ArticlesResponse("success", result));

	}

	@GetMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getArticles() {

		Article article = new Article();
		article.setContent("Contenido metido222");
		article.setTitle("Title metido222");
		article.setImage("Image metida222");

		// List<Article> result= new ArrayList<>();
		// result.add(article);
		List<Article> result = articleService.getArticles();
		System.out.println("ARTTITI" + result.size());

		return ResponseEntity.status(HttpStatus.OK).body(new ArticlesResponse("success", result));

	}

//	@GetMapping(value = "articles", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<Article> getArticles() {
//		return articleService.getArticles();
//
//	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveArticle(@RequestBody Article article) {
		System.out.println("Soy el SAVE");
		Article article2 = new Article();
		// article2.setId(2222);
		article2.setDate(new Date());
		article2.setTitle(article.getTitle());
		article2.setContent(article.getContent());
		article2.setImage(article.getImage());
		System.out.println("LLEGA SAVE");
		Article articleSaved = articleService.saveArticle(article2);
		return ResponseEntity.ok(new ArticleResponse("success", "Article SAVED", articleSaved));

	}

	@GetMapping(value = "/article/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getArticle(@PathVariable("id") int idArticle) {

		return ResponseEntity.ok(new ArticleResponse("success", "FOUND", articleService.getArticleSrv(idArticle)));
	}

	@PutMapping(value = "/articleUpdate/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateArticle(@PathVariable int id, @RequestBody Article article) {

		System.out.println("El param den l aurl " + id);
		Article result = articleService.updateArticleSrv(id, article);
		if (result != null) {

			ArticleResponse articleResponse = new ArticleResponse("success", "Article Updated", (Article) result);

			return ResponseEntity.status(HttpStatus.OK).body(articleResponse);
		} else {
			ArticleResponse articleResponse = new ArticleResponse("Error!!", "Article NOT EXIST!", null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(articleResponse);
		}
	}

	@DeleteMapping(value = "/articleDelete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable int id) {

		System.out.println("El param delete" + id);
		Article result = articleService.deleteArticleSrv(id);
		if (result != null) {

			ArticleResponse articleResponse = new ArticleResponse("success", "Article Deleted", result);
			return ResponseEntity.status(HttpStatus.OK).body(articleResponse);
		} else {
			ArticleResponse articleResponse = new ArticleResponse("Error!!", "Article NOT EXIST!", null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(articleResponse);
		}
	}

	@PostMapping(value = "/upload")
	public ResponseEntity<String> uploadFiles(@RequestParam("file0") List<MultipartFile> files) {

		try {
			for (MultipartFile file : files) {
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().length() - 3,
						file.getOriginalFilename().length());
				if (extension.equals("png") || extension.equals("jpg")) {

					fileService.save(files);
					return ResponseEntity.status(HttpStatus.OK).body("Los Archivos se cargaron");

				}
			}
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Archivos invalidos!!");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ocurrio un error al subir los archivos");

		}

	}

	@GetMapping(value = "/getImage/{image}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getImage(@PathVariable("image") String image) {

		Path rootFolder = Paths.get("uploads" + "/" + image);

		String fullPath = rootFolder.toString();
		File file = new File(fullPath);
		System.out.println("RUTA " + fullPath);
		if (file.exists()) {
			System.out.println("EXISTE");
			return ResponseEntity.ok(file);
		} else {
			System.out.println("NNOOO EXISTE");
		}
		return ResponseEntity.ok(new ArticleResponse("ok", "llega al image", null));
	}

	@GetMapping(value = "/search/{search}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> search(@PathVariable("search") String search) {

		List<Article> articles = articleService.searchArticlesSrv(search);
		if (articles.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ArticlesResponse("NOT FOUND",articles));
		} else {

			return ResponseEntity.ok(new ArticlesResponse("success", articles));

		}
	}
}
