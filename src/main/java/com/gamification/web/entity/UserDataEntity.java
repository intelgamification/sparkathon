package com.gamification.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DATA")
public class UserDataEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6515542907597853069L;
	private int id;
	private Date metricDate;
	private int aht;
	private int interactions;
	private int productionEfficiency;
	private PlatformUserEntity platformUser;

	@Id
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// @Column(name = "USER_ID")
	// public int getUserId() {
	// return userId;
	// }
	//
	// public void setUserId(int userId) {
	// this.userId = userId;
	// }

	@ManyToOne(optional = false)
	@JoinColumn(name = "USER_ID", referencedColumnName = "ENTITY_ID")
	public PlatformUserEntity getPlatformUser() {
		return platformUser;
	}

	public void setPlatformUser(PlatformUserEntity platformUser) {
		this.platformUser = platformUser;
	}

	@Column(name = "METRIC_DATE")
	public Date getMetricDate() {
		return metricDate;
	}

	public void setMetricDate(Date metricDate) {
		this.metricDate = metricDate;
	}

	@Column(name = "AHT")
	public int getAht() {
		return aht;
	}

	public void setAht(int aht) {
		this.aht = aht;
	}

	@Column(name = "INTERACTIONS")
	public int getInteractions() {
		return interactions;
	}

	public void setInteractions(int interactions) {
		this.interactions = interactions;
	}

	@Column(name = "PRODUCTION_EFFICIENCY")
	public int getProductionEfficiency() {
		return productionEfficiency;
	}

	public void setProductionEfficiency(int productionEfficiency) {
		this.productionEfficiency = productionEfficiency;
	}

}
