package com.gamification.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AHT_STREAM_DATA")
public class AhtStreamData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1033007442546272576L;

	private String id;
	private int minimumAht;
	private String agentName;
	private Date achievedDate;

	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="MINIMUM_AHT")
	public int getMinimumAht() {
		return minimumAht;
	}

	public void setMinimumAht(int minimumAht) {
		this.minimumAht = minimumAht;
	}
	
	@Column(name="AGENT_NAME")
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	

	@Column(name="ACHIEVED_DATE")
	public Date getAchievedDate() {
		return achievedDate;
	}

	public void setAchievedDate(Date achievedDate) {
		this.achievedDate = achievedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
