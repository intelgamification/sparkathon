package com.gamification.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gamification.web.entity.UserDataEntity;

public class UserDataDAOImpl implements UserDataDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public UserDataEntity getUserDataForUser(int entityId) {
		Session session= sessionFactory.getCurrentSession();
		UserDataEntity userData= (UserDataEntity) session.load(UserDataEntity.class, new Integer(entityId));
		
		return userData;
	}

	public List<UserDataEntity> getUserDataForAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserDataEntity>  userDatas = session.createCriteria(UserDataEntity.class).list();
		return userDatas;
	}

}
