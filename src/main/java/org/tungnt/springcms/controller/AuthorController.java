package org.tungnt.springcms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tungnt.springcms.entity.AuthorEnt;
import org.tungnt.springcms.service.AuthorService;
import org.tungnt.springcms.util.ResponseData;

@RestController
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	@GetMapping("api/authors")
	public ResponseEntity<String> getAllAuthor(){
		authorService.findAllAuthor();
		return ResponseEntity.ok("ok");
	}
}
