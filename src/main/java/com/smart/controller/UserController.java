package com.smart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserRepository userRepository;

	@ModelAttribute
	public void commonData(Model model, Principal principal){
		String username = principal.getName();
		User user = userRepository.getUserByUserName(username);
		model.addAttribute("user", user);
	}
	
	@RequestMapping("/dashboard")
	public String userDashboard(Model model) {
		model.addAttribute("title", "DashBoard");
		return "/user/userdashboard";
	}
	@RequestMapping("/profile")
	public String userProfilePage (Model model){
		model.addAttribute("title", "Profile");
		return "/user/userprofile";
	}

	@GetMapping("/addcontact")
	public String addContact(Model model){
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "user/addcontact";
	}

}
