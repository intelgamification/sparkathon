package com.gamification.web.model;

import java.util.Date;
import java.util.List;

public class GraphDetails {

	private Date date;

	private List<UserMetricData> data;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<UserMetricData> getData() {
		return data;
	}

	public void setData(List<UserMetricData> data) {
		this.data = data;
	}

}
