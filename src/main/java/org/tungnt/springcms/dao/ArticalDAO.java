package org.tungnt.springcms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.tungnt.springcms.entity.ArticalEnt;

@Repository
public class ArticalDAO extends AbstractHibernateDAO<ArticalEnt>{
	
	public ArticalDAO() {
		this.setClazz(ArticalEnt.class);
	}
	
	public ArticalEnt deleteArticleById(long id) {
		ArticalEnt tempArticalEnt = getCurrentSession().get(ArticalEnt.class, id);
		delete(tempArticalEnt);
		return tempArticalEnt;
	}
	
	public List<ArticalEnt> findTenArticleAtPage(long page){
		Criteria criteria = getCurrentSession().createCriteria(ArticalEnt.class);
		criteria.setMaxResults(10);
		page = page<=1 ? 1: page;
		System.out.println(page);
		criteria.add(Restrictions.le("id", (page-1)*10+10));
		criteria.add(Restrictions.ge("id", (page-1)*10+1));
		List<ArticalEnt> listEntResults = criteria.list();
		return listEntResults;
	}
	
	public List<ArticalEnt> findAll(){
		Criteria criteria = getCurrentSession().createCriteria(ArticalEnt.class);
		criteria.setMaxResults(10);
		List<ArticalEnt> listEntResults = criteria.list();
		return listEntResults;
	}
	
	public ArticalEnt save(ArticalEnt artical) {
		getCurrentSession().saveOrUpdate(artical);
		System.out.println("Artical saved with id " + artical.getId());
		return artical;
	}
}
