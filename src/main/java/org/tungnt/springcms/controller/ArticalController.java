package org.tungnt.springcms.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tungnt.springcms.dao.ArticalDAO;
import org.tungnt.springcms.entity.ArticalEnt;
import org.tungnt.springcms.entity.AuthorEnt;
import org.tungnt.springcms.form.ArticalForm;
import org.tungnt.springcms.service.ArticalService;
import org.tungnt.springcms.util.ResponseData;

@RestController
public class ArticalController {

	@Autowired
	ArticalService articleService;
	
	@GetMapping("api/articles")
	public ResponseEntity<ResponseData<List<ArticalEnt>>> getAllArticle(){
		List<ArticalEnt> listResults =  articleService.findAll();
		ResponseData<List<ArticalEnt>> results = new ResponseData<>();
		results.setData(listResults);
		results.setMessage("not thing to see here");
		results.setStatus("status.ok");
		return ResponseEntity.ok(results);
	}
	
	@GetMapping("api/articles/page")
	public ResponseEntity<ResponseData<List<ArticalEnt>>> getTenNextArticle(@RequestParam(name="page") long page){
		
		List<ArticalEnt> listResults =  articleService.findTenArticalsAtPage(page);
		ResponseData<List<ArticalEnt>> results = new ResponseData<>();
		results.setData(listResults);
		results.setMessage("not thing to see here");
		results.setStatus("status.ok");
		return ResponseEntity.ok(results);
	}
	
	@DeleteMapping("api/articles")
	public ResponseEntity<ResponseData<ArticalEnt>> deleteArticle(@RequestParam(name="id") long id){
		ArticalEnt tempArtical = articleService.deleteArticleById(id);
		ResponseData<ArticalEnt> response = new ResponseData<>();
		response.setStatus("status.ok");
		response.setMessage("deleteing complete");
		response.setData(tempArtical);
		return ResponseEntity.ok(response);
	}
}
