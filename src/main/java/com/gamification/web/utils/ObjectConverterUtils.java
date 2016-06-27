/*package com.gamification.web.utils;

import com.gamification.web.entity.PlatformUserEntity;
import com.gamification.web.entity.UserDataEntity;
import com.gamification.web.model.PlatformUser;
import com.gamification.web.model.UserData;

public class ObjectConverterUtils {
	
	public static PlatformUser convertPlatformUserEntityToModel(PlatformUserEntity platformUserEntity){
		PlatformUser platformUser = new PlatformUser();
		platformUser.setEntityId(platformUserEntity.getEntityId());
		platformUser.setFirstName(platformUserEntity.getFirstName());
		platformUser.setId(platformUserEntity.getId());
		platformUser.setLastName(platformUserEntity.getLastName());
		platformUser.setUserName(platformUserEntity.getUserName());
		platformUser.setUserData(convertUserDataEntityToModel(platformUserEntity.getUserData()));
		return platformUser;
		
	}

	public static UserData convertUserDataEntityToModel(UserDataEntity userDataEntity) {
		UserData userData = new UserData();
		userData.setUserId(userDataEntity.getUserId());
		userData.setAht(userDataEntity.getAht());
		userData.setInteractions(userDataEntity.getInteractions());
		userData.setMetricDate(userDataEntity.getMetricDate());
		userData.setProductionEfficiency(userDataEntity.getProductionEfficiency());
		return userData;
	}

}
*/