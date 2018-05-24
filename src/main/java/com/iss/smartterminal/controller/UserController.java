package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iss.smartterminal.entity.User;

@Controller
public class UserController {

	@RequestMapping("/user")
	public ModelAndView getIndex() {
		
		ModelAndView mav= new ModelAndView(); 
		User user = new User("a", "2", "q");
		mav.addObject("user", user);
	    mav.addObject("name","this name");  
	    mav.setViewName("patients");  
	    return mav;  
	}
	
//	@RequestMapping("/register")
//	public ModelAndView register() {
//		
//		ModelAndView mav= new ModelAndView(); 
//		User user = new User(1, "2", "q");
//		mav.addObject("user", user);
//	    mav.addObject("name","this name");  
//	    mav.setViewName("register");
//	    
//	    return mav;  
//	}
//	
//	@RequestMapping("/insert")
//	public ModelAndView insert() {
//		
//		ModelAndView mav= new ModelAndView(); 
//		User user = new User(1, "2", "q");
//		mav.addObject("user", user);
//	    mav.addObject("name","this name");  
//	    mav.setViewName("patients");
//	    
//	    return mav;  
//	}
}