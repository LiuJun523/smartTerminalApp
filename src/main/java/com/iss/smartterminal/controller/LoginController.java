package com.iss.smartterminal.controller;

import javax.servlet.http.HttpSession;

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

		return new ModelAndView("redirect:/doctor/login");
	}

	@RequestMapping(value = "/doctor/login", method = RequestMethod.GET)
	public ModelAndView loginPage(String nirc) {

		ModelAndView mav = new ModelAndView("index");
		mav.addObject("nirc", nirc);
		
		return mav;
	}

	@RequestMapping(value = "/doctor/login", method = RequestMethod.POST)
	@ResponseJSONP
	public String login(@ModelAttribute("lPojo") LoginPojo lPojo, HttpSession session ) {

		return new LoginApp().doAction(lPojo, session);
	}
}