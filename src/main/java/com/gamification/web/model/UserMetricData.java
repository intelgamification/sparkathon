package com.gamification.web.model;

/**
 * @author pdhoke
 *
 */
public class UserMetricData {

	private String name;
	private String metric;
	private int value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
