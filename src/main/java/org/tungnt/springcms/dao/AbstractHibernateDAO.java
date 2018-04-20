package org.tungnt.springcms.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AbstractHibernateDAO<T>{
	
	private Class<T> clazz;
	
	public void setClazz(Class<T> classToSet) {
		this.clazz = classToSet;
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public T findById(long id) {
		return getCurrentSession().get(this.clazz, id);
	}
	
	public void create(T entity) {
		getCurrentSession().save(entity);
	}
	
	public void update(T entity) {
		getCurrentSession().update(entity);
	}
	
	public void delete(T entity) {
		Session session = getCurrentSession();
		session.delete(session.contains(entity) ? entity : session.merge(entity));
	}
}
 