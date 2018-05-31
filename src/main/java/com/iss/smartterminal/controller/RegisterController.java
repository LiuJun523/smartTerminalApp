package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.app.RegisterApp;
import com.iss.smartterminal.pojo.RegisterPojo;

@Controller
public class RegisterController {

	@RequestMapping(value = "/doctor/new", method = RequestMethod.GET)
	public ModelAndView registerPage() {

		return new ModelAndView("register");
	}

	@RequestMapping(value = "/doctor/register", method = RequestMethod.POST)
	@ResponseJSONP
	public String register(@ModelAttribute("rPojo") RegisterPojo rPojo) {
		
		return new RegisterApp().doAction(rPojo);
	}
}
