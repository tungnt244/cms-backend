package org.tungnt.springcms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.tungnt.springcms.entity.UserEntity;

@Repository
public class UserDAO extends AbstractHibernateDAO<UserEntity>{
	
	public UserEntity findUserByName(String name) {
		Criteria cr = (Criteria) getCurrentSession().createCriteria(UserEntity.class);
		cr.add(Restrictions.eq("username", name));
		cr.add(Restrictions.eq("isDelete", false));
		List<UserEntity> userList =  cr.list();
		if(userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}
}
