package org.tungnt.springcms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tungnt.springcms.dao.ArticalDAO;
import org.tungnt.springcms.dao.AuthorDAO;
import org.tungnt.springcms.entity.ArticalEnt;
import org.tungnt.springcms.entity.AuthorEnt;
import org.tungnt.springcms.entity.CommentEntity;
import org.tungnt.springcms.form.ArticalForm;

@Service
public class ArticalService {

	@Autowired
	ArticalDAO articalDAO;
	
	@Autowired
	AuthorDAO authorDAO;
	
	public void createNewArticle(ArticalEnt newArtical) {
		//author found
		AuthorEnt tempAuthor = authorDAO.findAuthorByName(newArtical.getAuthor().getName());
		if(tempAuthor != null) {
			newArtical.setAuthor(tempAuthor);
		}
		//author not found
		articalDAO.create(newArtical);
	}
	
	public ArticalEnt deleteArticleById(long articlaId) {
		return articalDAO.deleteArticleById(articlaId);
	}
	
	public List<ArticalEnt> findAll() {
		List<ArticalEnt> listArticles = articalDAO.findAll();
		return listArticles;
	}
	
	public List<ArticalEnt> findTenArticalsAtPage(long page){
		return articalDAO.findTenArticleAtPage(page);
	}
}
