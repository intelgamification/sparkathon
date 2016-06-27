package com.gamification.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gamification.web.entity.AhtStreamData;

@Repository
public class AhtStreamDataDAOImpl implements AhtStreamDataDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public AhtStreamData getAhtStream() {
		Session session=sessionFactory.getCurrentSession();
		AhtStreamData ahtStreamData=(AhtStreamData) session.get(AhtStreamData.class, "minAht");		
		return ahtStreamData;
	}

}
