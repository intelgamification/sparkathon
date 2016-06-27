package com.gamification.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.genetics.ListPopulation;
import org.apache.xerces.dom.DOMXSImplementationSourceImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gamification.web.dao.PlatformUserDAO;
import com.gamification.web.entity.PlatformUserEntity;
import com.gamification.web.model.PlatformUser;

@Service("platformUserService")
public class PlatformUserServiceImpl implements PlatformUserService {

	@Autowired
	private PlatformUserDAO platformUserDAO;
	@Autowired
	private Mapper dozerMapper;

	public Mapper getDozerMapper() {
		return dozerMapper;
	}

	public void setDozerMapper(Mapper dozerMapper) {
		this.dozerMapper = dozerMapper;
	}

	public PlatformUserDAO getPlatformUserDAO() {
		return platformUserDAO;
	}

	public void setPlatformUserDAO(PlatformUserDAO platformUserDAO) {
		this.platformUserDAO = platformUserDAO;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PlatformUser getUserById(String entityId) {
		PlatformUserEntity platformUserEntity = platformUserDAO.getUserById(entityId);

		PlatformUser platformUser = dozerMapper.map(platformUserEntity, PlatformUser.class);
		System.out.println(platformUser);
		return platformUser;
	}

	public PlatformUser getUsersByIds(List<String> entityIds) {
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<PlatformUser> getUsersAHTByIds(List<String> entityIds) {
		List<PlatformUserEntity> platformUserEntities = platformUserDAO.getUsersAHTByIds(entityIds);
		List<PlatformUser> platformUsers = new ArrayList<PlatformUser>();

		for (PlatformUserEntity entity : platformUserEntities) {
			platformUsers.add(dozerMapper.map(entity, PlatformUser.class));
		}
		return platformUsers;
	}

}
