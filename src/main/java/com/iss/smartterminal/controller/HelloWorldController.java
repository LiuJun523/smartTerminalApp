package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String sayHello(ModelMap model) {
		model.addAttribute("message", "Welcome from Spring MVC");
		
		return "welcome";
	}
	
	@RequestMapping(value="/helloAgain", method=RequestMethod.GET)
	public String sayHelloAgain(ModelMap model) {
		model.addAttribute("message", "Welcome Again from Spring MVC");
		
		return "welcome";
	}
}
