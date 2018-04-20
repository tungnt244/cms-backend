package org.tungnt.springcms;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tungnt.springcms.dao.ArticalDAO;
import org.tungnt.springcms.dao.AuthorDAO;
import org.tungnt.springcms.entity.ArticalEnt;
import org.tungnt.springcms.entity.AuthorEnt;
import org.tungnt.springcms.entity.CommentEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticalDAOTest {

	@Autowired
	ArticalDAO articalDAO;
	
	@Autowired
	AuthorDAO authorDAO;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testInsert() {
		ArticalEnt tempArtical = new ArticalEnt();
		tempArtical.setTitle("new artical title");
		tempArtical.setContent("new content for artical");
		tempArtical.setCreatedDate(new Date());
		
		AuthorEnt tempAuthor = new AuthorEnt();
		tempAuthor.setName("tung");
		tempAuthor.setDateOfBirth(new Date());
		tempAuthor.addArtical(tempArtical);
		
		CommentEntity tempComment = new CommentEntity();
		tempComment.setContent("new comment");
		
		tempArtical.addComment(tempComment);
		articalDAO.save(tempArtical);
		assertNotNull(tempArtical.getId());
	}
	
	
	@Test
	public void contextLoads() {
	}

}
