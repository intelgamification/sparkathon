package com.gamification.web.model;

import java.util.Date;

public class UserData {

	private int id;
	private Date metricDate;
	private int aht;
	private int interactions;
	private int productionEfficiency;
	private PlatformUser platformUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PlatformUser getPlatformUser() {
		return platformUser;
	}

	public void setPlatformUser(PlatformUser platformUser) {
		this.platformUser = platformUser;
	}

	public Date getMetricDate() {
		return metricDate;
	}

	public void setMetricDate(Date metricDate) {
		this.metricDate = metricDate;
	}

	public int getAht() {
		return aht;
	}

	public void setAht(int aht) {
		this.aht = aht;
	}

	public int getInteractions() {
		return interactions;
	}

	public void setInteractions(int interactions) {
		this.interactions = interactions;
	}

	public int getProductionEfficiency() {
		return productionEfficiency;
	}

	public void setProductionEfficiency(int productionEfficiency) {
		this.productionEfficiency = productionEfficiency;
	}

}
