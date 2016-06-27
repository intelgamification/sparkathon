package com.gamification.web.dao;

import java.util.List;

import com.gamification.web.entity.UserDataEntity;

public interface UserDataDAO {
	UserDataEntity getUserDataForUser(int entityId);
	
	List<UserDataEntity>getUserDataForAllUsers();

}
