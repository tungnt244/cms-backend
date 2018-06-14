package org.tungnt.springcms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tungnt.springcms.form.UserForm;
import org.tungnt.springcms.service.UserAuthenticationService;

@RestController
public class AuthenticationController {

	@Autowired
	UserAuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserForm userForm) {

		Optional<String> result = authenticationService.login(userForm);

		if (result.isPresent()) {
			return ResponseEntity.ok(result.get());
		}
		return ResponseEntity.status(401).build();
	}
}
