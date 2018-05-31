package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.app.LoginApp;
import com.iss.smartterminal.pojo.LoginPojo;

@Controller
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView root() {

		return new ModelAndView("index");
	}

	@RequestMapping(value = "/doctor/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {

		return new ModelAndView("index");
	}

	@RequestMapping(value = "/doctor/login", method = RequestMethod.POST)
	@ResponseJSONP
	public ModelAndView login(@ModelAttribute("lPojo") LoginPojo lPojo) {

		return new LoginApp().doAction(lPojo);
	}
}