package com.gamification.web.model;

public class ReportAgentData {
	private String agentName;
	private int aht;
	private int interactions;
	private int prodEfficiency;

	public int getAgentPersona() {
		return (100 - aht) + interactions + prodEfficiency;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
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

	public int getProdEfficiency() {
		return prodEfficiency;
	}

	public void setProdEfficiency(int prodEfficiency) {
		this.prodEfficiency = prodEfficiency;
	}

}
