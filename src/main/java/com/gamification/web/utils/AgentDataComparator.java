package com.gamification.web.utils;

import java.util.Comparator;

import com.gamification.web.model.ReportAgentData;

public class AgentDataComparator implements Comparator<ReportAgentData> {

	public int compare(ReportAgentData o1, ReportAgentData o2) {
		
		return o1.getAgentName().compareTo(o2.getAgentName());
	}

}
