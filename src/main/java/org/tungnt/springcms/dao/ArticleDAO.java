package org.tungnt.springcms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.tungnt.springcms.entity.ArticleEnt;

@Repository
public class ArticleDAO extends AbstractHibernateDAO<ArticleEnt> {

	public ArticleDAO() {
		this.setClazz(ArticleEnt.class);
	}

	public ArticleEnt deleteArticleById(long id) {
		ArticleEnt tempArticleEnt = getCurrentSession().get(ArticleEnt.class, id);
		delete(tempArticleEnt);
		return tempArticleEnt;
	}

	public List<ArticleEnt> findTenArticleAtPage(long page) {
		Criteria criteria = getCurrentSession().createCriteria(ArticleEnt.class);
		criteria.setMaxResults(10);
		page = page <= 1 ? 1 : page;
		System.out.println(page);
		criteria.add(Restrictions.le("id", (page - 1) * 10 + 10));
		criteria.add(Restrictions.ge("id", (page - 1) * 10 + 1));
		List<ArticleEnt> listEntResults = criteria.list();
		return listEntResults;
	}

	public List<ArticleEnt> findAll() {
		Criteria criteria = getCurrentSession().createCriteria(ArticleEnt.class);
		criteria.setMaxResults(10);
		List<ArticleEnt> listEntResults = criteria.list();
		return listEntResults;
	}

	@Override
	public void create(ArticleEnt article) {
		getCurrentSession().saveOrUpdate(article);
		System.out.println("Article saved with id " + article.getId());
	}
	
	@Override
	public void delete(ArticleEnt article) {
		getCurrentSession().save(article);
		System.out.println("Article deleted with id " + article.getId());
	}
	
	public List<ArticleEnt> findFourArticle(int page) {
		Criteria criteria = getCurrentSession().createCriteria(ArticleEnt.class);
		criteria.addOrder(Order.desc("createdDate"));
		criteria.setFirstResult((page-1)*4);
		criteria.setMaxResults(4);
		List<ArticleEnt> listResults = criteria.list();
		return listResults;
	}

	public long getTotalNumberArticle() {
		Criteria criteria = getCurrentSession().createCriteria(ArticleEnt.class);
		criteria.setProjection(Projections.rowCount());
		return (long) criteria.uniqueResult();
	}
}
