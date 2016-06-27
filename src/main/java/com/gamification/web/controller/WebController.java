package com.gamification.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamification.web.model.PlatformUser;
import com.gamification.web.service.PlatformUserService;

@Controller
public class WebController {

	@Autowired
	private PlatformUserService platformUserService;

	public PlatformUserService getPlatformUserService() {
		return platformUserService;
	}

	public void setPlatformUserService(PlatformUserService platformUserService) {
		this.platformUserService = platformUserService;
	}

	@RequestMapping("/test")
	public String sayHi(HttpServletRequest request, Model model) {
		return "hello";
	}

	@RequestMapping("")
	public String getUser() {
		System.out.println("Hi");
		PlatformUser platformUser=	platformUserService.getUserById("1141");
		System.out.println("data received: " + platformUser.getUsersData().size());
		return "hello";
	}
}