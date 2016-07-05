package com.gamification.domain;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;

import com.gamification.streaming.SparkStreamingUserDataGenerator;

public class UserData implements Serializable{

	private static final long serialVersionUID = 2856835962680922938L;	
	private static final String DELIMITER = " ";
	private static final Log LOGGER = LogFactory.getLog(UserData.class);
	
	private String firstName;
	private String lastName;
	private String userId;
	private Date metricDate;
	private int aht;
	private int interactions;
	private int productionEfficiency;
	
	public UserData(){
		
	}
	
	private UserData(String firstName, String lastName, String userId, Date metricDate, int aht, int interactions,
			int productionEfficiency) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.metricDate = metricDate;
		this.aht = aht;
		this.interactions = interactions;
		this.productionEfficiency = productionEfficiency;
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
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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

	public static UserData parseFromStream(String stream){
		System.out.println(stream);
		String[] streamArr = stream.split(DELIMITER);
		LOGGER.debug(streamArr);
		System.out.println(streamArr);
		return new UserData(streamArr[0],
				streamArr[1],
				streamArr[2],
				Date.valueOf(streamArr[3]),
				Integer.parseInt(streamArr[4]),
				Integer.parseInt(streamArr[5]),
				Integer.parseInt(streamArr[6]));
	}

	@Override
	public String toString() {
		return "UserData [firstName=" + firstName + ", lastName=" + lastName
				+ ", userId=" + userId + ", metricDate=" + metricDate
				+ ", aht=" + aht + ", interactions=" + interactions
				+ ", productionEfficiency=" + productionEfficiency + "]";
	}
	
}
