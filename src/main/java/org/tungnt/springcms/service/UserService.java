package org.tungnt.springcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tungnt.springcms.dao.UserDAO;
import org.tungnt.springcms.entity.UserEntity;
import org.tungnt.springcms.form.UserForm;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	PasswordEncoder passwordEncoder;

	public void addUser(UserForm userForm) {
		String encodedPassword = passwordEncoder.encode(userForm.getPassword());

		UserEntity entity = UserEntity.builder().username(userForm.getUsername()).password(encodedPassword).build();
		userDAO.create(entity);
	}

}
