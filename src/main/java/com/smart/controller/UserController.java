package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/dashboard")
	public String userDashboard(Model model) {
		model.addAttribute("title", "DashBoard");
		return "/user/userdashboard";
	}
	@RequestMapping("/profile")
	public String userProfilePage (Model model){
		model.addAttribute("title", "Profile");
		return "userprofile";
	}

}
