package com.gamification.web.dao;

import java.util.List;

import com.gamification.web.entity.PlatformUserEntity;

public interface PlatformUserDAO {
	
	List<PlatformUserEntity> getUsersByIds(List<String> entityIds);
	
	PlatformUserEntity getUserById(String entityId);
	
	List<PlatformUserEntity> getUsersAHTByIds(List<String> entityIds);

}
