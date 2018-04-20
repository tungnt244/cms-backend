package org.tungnt.springcms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.tungnt.springcms.entity.AuthorEnt;

@Repository
public class AuthorDAO extends AbstractHibernateDAO<AuthorEnt>{
	
	public AuthorDAO() {
		this.setClazz(AuthorEnt.class);
	}
	
	@SuppressWarnings("deprecation")
	public AuthorEnt findAuthorByName(String name) {
		Criteria cr = getCurrentSession().createCriteria(AuthorEnt.class);
		cr.add(Restrictions.eq("name", name));
		List<AuthorEnt> listAuth = cr.list();
		if(!listAuth.isEmpty()) {
			return (AuthorEnt) cr.list().get(0);
		}
		return null;
	}
	
	public List<AuthorEnt> findAllAuthor(){
		List<AuthorEnt> lists = getCurrentSession().createQuery("from AuthorEnt a").list();
		return lists;
	}
	
	public AuthorEnt save(AuthorEnt author) {
		getCurrentSession().saveOrUpdate(author);
		System.out.println("Artical saved with id " + author.getId());
		return author;
	}
}
