package com.gamification.web.model;

import java.util.ArrayList;
import java.util.List;

public class ReportDateData {
	private String reportDate;
	private List<ReportAgentData> agentData=new ArrayList<ReportAgentData>();
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public List<ReportAgentData> getAgentData() {
		return agentData;
	}
	public void setAgentData(List<ReportAgentData> agentData) {
		this.agentData = agentData;
	}
	
	
}
