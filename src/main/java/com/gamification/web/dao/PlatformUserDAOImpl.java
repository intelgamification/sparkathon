package com.gamification.web.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.spark.unsafe.Platform;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamification.web.entity.PlatformUserEntity;

import tachyon.thrift.FileSystemMasterService.AsyncProcessor.create;

@Repository
public class PlatformUserDAOImpl implements PlatformUserDAO{
	Logger logger = Logger.getLogger(PlatformUserDAOImpl.class);

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<PlatformUserEntity> getUsersByIds(List<String> entityIds) {
		Session session = this.sessionFactory.getCurrentSession();
		List<PlatformUserEntity> platformUsers = session.createCriteria(PlatformUserEntity.class)
				.add(Restrictions.in("entityId", entityIds)).list();
		logger.info("Platform users fetched in  getUsersByIds() of PlatformUserDAOImpl  is: " + platformUsers);
		return platformUsers;
	}

	public PlatformUserEntity getUserById(String entityId) {
		Session session = this.sessionFactory.getCurrentSession();		
		String hql="FROM PlatformUserEntity PU WHERE PU.entityId like " + entityId ;
		Query query =session.createQuery(hql);
		PlatformUserEntity platformUserEntity = (PlatformUserEntity) query.uniqueResult();
		logger.info("Platform user for id " + entityId + "  is "+platformUserEntity);
		return platformUserEntity;
	}

	public List<PlatformUserEntity> getUsersAHTByIds(List<String> entityIds) {
		Session session = this.sessionFactory.getCurrentSession();		
		String hql="FROM PlatformUserEntity PU WHERE PU.entityId IN (:entityIds)" ;
		Query query =session.createQuery(hql).setParameterList("entityIds", entityIds);
		List<PlatformUserEntity> platformUserEntities =  query.list();
		return platformUserEntities;	
	}
	
}
