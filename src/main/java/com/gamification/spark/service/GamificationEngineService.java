package com.gamification.spark.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gamification.spark.engine.GamificationEngine;

@RestController  
@RequestMapping("/engine")
public class GamificationEngineService {
	
	@Autowired
	GamificationEngine gamificaitonEngine;
	
	@RequestMapping(value = "/invoke", method = RequestMethod.GET, headers="Accept=application/json")
	public String invokeEngine(HttpServletRequest request ,Model model){
		Date startDate = new Date();
		gamificaitonEngine.invokeEngine();
		Date endDate = new Date();
		long duration = endDate.getTime() - startDate.getTime();
		return "Gmaificaiton Engine completed in " + duration/1000 + " seconds.";
	}
}
