package org.tungnt.springcms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tungnt.springcms.dao.ArticalDAO;
import org.tungnt.springcms.dao.AuthorDAO;
import org.tungnt.springcms.entity.AuthorEnt;

@Service
public class AuthorService {

	@Autowired
	AuthorDAO authorDao;
	
	public void findAllAuthor(){
		List<AuthorEnt> lists = authorDao.findAllAuthor();
		System.out.println(lists.get(0).getListArticals());
	}
}
