package com.gamification.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {
	
	@RequestMapping("/test")
	public String sayHi(HttpServletRequest request ,Model model){
		return "hello";
	}
}
