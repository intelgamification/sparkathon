package com.gamification.web.service;

import java.util.List;

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

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public PlatformUser getUserById(String entityId) {
		PlatformUserEntity platformUserEntity = platformUserDAO.getUserById(entityId);

		PlatformUser platformUser = dozerMapper.map(platformUserEntity, PlatformUser.class);
		System.out.println(platformUser);
		return platformUser;
	}

	public PlatformUser getUsersByIds(List<String> entityIds) {
		return null;
	}

}
