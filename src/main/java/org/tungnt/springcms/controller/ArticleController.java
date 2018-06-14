package org.tungnt.springcms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tungnt.springcms.entity.ArticleEnt;
import org.tungnt.springcms.form.ArticleForm;
import org.tungnt.springcms.form.ArticleSummaryForm;
import org.tungnt.springcms.service.ArticleService;

@RestController
@CrossOrigin
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@GetMapping("/api/article/list/summary/{page}")
	public ResponseEntity<?> getListSummaries(@PathVariable("page") int page) {
		List<ArticleSummaryForm> lists = articleService.findFourSummaryArticle(page);
		if (lists.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not find posts");
		}
		return ResponseEntity.ok(lists);
	}
	
	@GetMapping("/api/article/total")
	public ResponseEntity<?> getNumberArticle(){
		return ResponseEntity.ok(articleService.getTotalNumberArticle());
	}

	@GetMapping("/api/article/{id}")
	public ResponseEntity<?> getSingleArticle(@PathVariable(name = "id") long id) {
		Optional<ArticleEnt> articleOptional = articleService.findArticleById(id);
		if (!articleOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not find article");
		}
		ArticleEnt article = articleOptional.get();
		ArticleForm temp = ArticleForm.builder().id(article.getId()).title(article.getTitle())
				.subTitle(article.getSubTitle()).content(article.getContent()).createdDate(article.getCreatedDate())
				.build();
		return ResponseEntity.ok(temp);
	}

	@PostMapping("/api/admin/article/create")
	public ResponseEntity<?> createArticle(@RequestBody ArticleForm newArticleForm) {
		ArticleEnt newArticle = ArticleEnt.builder().title(newArticleForm.getTitle())
				.subTitle(newArticleForm.getSubTitle()).content(newArticleForm.getContent()).build();
		articleService.create(newArticle);
		return ResponseEntity.ok("created post");
	}

	@PutMapping("/api/admin/article/update")
	public ResponseEntity<?> updateArticle(@RequestBody ArticleEnt updateArticle,
			@RequestParam(name = "id") long articleId) {
		return null;
		// articleService.updateArticleById(updateArticle, articleId);
		// return ResponseEntity.ok("updated");
	}

	@PostMapping("/api/admin/article/delete")
	public ResponseEntity<?> deleteArticle(@RequestParam(name = "id") long id) {
		// ArticleEnt desiredDeleteArticle = articleDAO.findById(id);
		// desiredDeleteArticle.setIsDeleted(true);
		return null;
		// articleService.deleteArticleById(id);
		// return ResponseEntity.ok("deleted article");
	}
}
