package org.tungnt.springcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tungnt.springcms.dao.UserDAO;

@Service
public class TUserDetailsService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("call load user by name");
		UserDetails user = userDAO.findUserByName(username);
		System.out.println("user retrieve from this: " + user.getUsername() +" \n pass: "+ user.getPassword() + "\n authorities "+ user.getAuthorities());
		return user;
	}
}
