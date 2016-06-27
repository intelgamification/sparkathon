package com.gamification.spark.service;

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
		gamificaitonEngine.invokeEngine();
		return "hello";
	}
}
