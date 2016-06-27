package com.gamification.web.service;

import java.util.List;

import com.gamification.web.model.PlatformUser;

public interface PlatformUserService {

	PlatformUser getUserById(String entityId);

	PlatformUser getUsersByIds(List<String> entityIds);

}
