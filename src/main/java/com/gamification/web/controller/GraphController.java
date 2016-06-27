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


}
