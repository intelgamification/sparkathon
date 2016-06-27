package com.gamification.web.model;

import java.io.Serializable;
import java.util.Date;

public class AhtStreamData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1033007442546272576L;

	private String id;
	private int minimumAht;
	private String agentName;
	private Date achievedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMinimumAht() {
		return minimumAht;
	}

	public void setMinimumAht(int minimumAht) {
		this.minimumAht = minimumAht;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Date getAchievedDate() {
		return achievedDate;
	}

	public void setAchievedDate(Date achievedDate) {
		this.achievedDate = achievedDate;
	}	

}
