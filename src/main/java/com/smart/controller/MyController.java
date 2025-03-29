package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.validation.Valid;





@Controller
public class MyController {

	@Autowired
	public UserRepository userRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Home:Smart Contact Manager");
		return"home";
	}
	
	
	@RequestMapping("/about")
	public String aboutPage(Model model) {
		model.addAttribute("title", "About:Smart Contact Manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String requestMethodName(Model model) {
		
		model.addAttribute("title", "Register:Smart Contact Manager");
		
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
public String registerUser(@Valid
		@ModelAttribute("user") User user,
                           @RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
                           Model model, RedirectAttributes redirectAttributes, BindingResult result1) {
    try {
        System.out.println("Agreement : " + agreement);
        System.out.println("User : " + user);

        if (!agreement) {
            System.out.println("You have not agreed to the terms and conditions");
            throw new Exception("You have not agreed to the terms and conditions");
        }
        
        if(result1.hasErrors()) {
        	
//        	model.addAttribute("user" , user);
        	return "signup";
        }

        user.setEnable(true);
        user.setRole("ROLE_USER");

        User result = this.userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", new Message("Successfully registered!!", "alert-success"));

        return "redirect:/signup";  // Redirect instead of returning signup page directly

    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("user", user);
        redirectAttributes.addFlashAttribute("message", new Message("Something Went Wrong!! " + e.getMessage(), "alert-danger"));

        return "redirect:/signup";  // Redirect so flash attributes work properly
    }
}
	
	
}
