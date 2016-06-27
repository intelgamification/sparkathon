package com.gamification.web.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PLATFORM_USER")
public class PlatformUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -242155942291752607L;
	private int id;
	private String entityId;
	private String firstName;
	private String lastName;
	private String userName;
	private List<UserDataEntity> usersData;

	@OneToMany(mappedBy = "platformUser")
	public List<UserDataEntity> getUsersData() {
		return usersData;
	}

	public void setUsersData(List<UserDataEntity> usersData) {
		this.usersData = usersData;
	}

	@Id
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ENTITY_ID")
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
