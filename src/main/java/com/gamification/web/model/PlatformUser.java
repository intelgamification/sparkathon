package com.gamification.web.model;

import java.util.List;

import com.gamification.web.entity.UserDataEntity;

public class PlatformUser {
	private int id;
	private String entityId;
	private String firstName;
	private String lastName;
	private String userName;
	private List<UserData> usersData;

	public List<UserData> getUsersData() {
		return usersData;
	}

	public void setUsersData(List<UserData> usersData) {
		this.usersData = usersData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
