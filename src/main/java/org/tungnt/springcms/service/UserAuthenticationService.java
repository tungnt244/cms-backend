package org.tungnt.springcms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tungnt.springcms.entity.UserEntity;
import org.tungnt.springcms.form.UserForm;
import org.tungnt.springcms.util.JwtHandler;

@Service
public class UserAuthenticationService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("adminAuthPro")
	DaoAuthenticationProvider authenticationProvider;
	
	@Transactional
	public Optional<String> login(UserForm userForm){
		System.out.println("in user auth service");
		String token = null;
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword());
		Authentication auth = null;
		try {
			auth = authenticationProvider.authenticate(authentication);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return Optional.empty();
			// TODO: handle exception
		}
		if(auth.isAuthenticated()) {
			UserEntity user = (UserEntity) auth.getPrincipal();
			token = JwtHandler.createToken(user.getUsername(), user.getRole());
		}
		return Optional.ofNullable(token);
	}
}
