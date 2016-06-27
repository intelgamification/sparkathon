package com.gamification.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamification.web.model.GraphDetails;
import com.gamification.web.model.PlatformUser;
import com.gamification.web.model.ReportAgentData;
import com.gamification.web.model.ReportChartData;
import com.gamification.web.model.ReportDateData;
import com.gamification.web.model.UserData;
import com.gamification.web.service.PlatformUserService;
import com.gamification.web.utils.AgentDataComparator;
import com.gamification.web.utils.DateUtil;
import com.gamification.web.utils.ReportDateDataComparator;

@Controller
public class GraphController {
	@Autowired
	private PlatformUserService platformUserService;

	public PlatformUserService getPlatformUserService() {
		return platformUserService;
	}

	public void setPlatformUserService(PlatformUserService platformUserService) {
		this.platformUserService = platformUserService;
	}

	@RequestMapping("get-participants-aht-details")
	public ReportChartData getParticipantsAHT() {
		List<String> participants = Arrays.asList("1104","1176","1036","1228","1096");
		List<PlatformUser> platformUsers = platformUserService.getUsersAHTByIds(participants);
		ReportChartData chartData =new ReportChartData();
		Map<String, ReportDateData> responseMap = new HashMap<String, ReportDateData>();
		for (PlatformUser user : platformUsers) {
			for(UserData userData : user.getUsersData()){
				Date metricDate=userData.getMetricDate();
				String stringDate= DateUtil.getStringFormattedDate(metricDate);
				ReportDateData value = responseMap.get(stringDate);
				if(value == null){
					value = new ReportDateData();
					value.setReportDate(stringDate);					
					responseMap.put(stringDate, value);
				}
				ReportAgentData agentData = new ReportAgentData();
				agentData.setAgentName(user.getFirstName() +" "+user.getLastName());
				agentData.setAht(userData.getAht());
				agentData.setInteractions(userData.getInteractions());
				agentData.setProdEfficiency(userData.getProductionEfficiency());
				value.getAgentData().add(agentData);
			}
			
		}
		
		List<ReportDateData> reportDateDatas = new ArrayList<ReportDateData>(responseMap.values());
		Collections.sort(reportDateDatas, new ReportDateDataComparator());
		for(ReportDateData dateData: reportDateDatas){
			Collections.sort(dateData.getAgentData(), new AgentDataComparator());
		}
		chartData.setDateData(reportDateDatas);
		

		return chartData;
	}
	
	
	@RequestMapping("get-unparticipants-aht-details")
	public ReportChartData getUnparticipantsAHT() {
		List<String> participants = Arrays.asList("1104","1176","1036","1228","1096");
		List<PlatformUser> platformUsers = platformUserService.getUsersAHTByIds(participants);
		ReportChartData chartData =new ReportChartData();
		Map<String, ReportDateData> responseMap = new HashMap<String, ReportDateData>();
		for (PlatformUser user : platformUsers) {
			for(UserData userData : user.getUsersData()){
				Date metricDate=userData.getMetricDate();
				String stringDate= DateUtil.getStringFormattedDate(metricDate);
				ReportDateData value = responseMap.get(stringDate);
				if(value == null){
					value = new ReportDateData();
					value.setReportDate(stringDate);					
					responseMap.put(stringDate, value);
				}
				ReportAgentData agentData = new ReportAgentData();
				agentData.setAgentName(user.getFirstName() +" "+user.getLastName());
				agentData.setAht(userData.getAht());
				agentData.setInteractions(userData.getInteractions());
				agentData.setProdEfficiency(userData.getProductionEfficiency());
				value.getAgentData().add(agentData);
			}
			
		}
		
		List<ReportDateData> reportDateDatas = new ArrayList<ReportDateData>(responseMap.values());
		Collections.sort(reportDateDatas, new ReportDateDataComparator());
		for(ReportDateData dateData: reportDateDatas){
			Collections.sort(dateData.getAgentData(), new AgentDataComparator());
		}
		chartData.setDateData(reportDateDatas);
		

		return chartData;
	}
	
	
	

}
