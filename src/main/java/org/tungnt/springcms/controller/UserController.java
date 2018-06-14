package org.tungnt.springcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tungnt.springcms.form.UserForm;
import org.tungnt.springcms.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/api/register")
	public ResponseEntity<String> registerUser(@RequestBody UserForm userForm){
		userService.addUser(userForm);
		return ResponseEntity.ok("registered");
	}
}
