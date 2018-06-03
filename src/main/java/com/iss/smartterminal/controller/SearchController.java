package com.iss.smartterminal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.app.SearchApp;
import com.iss.smartterminal.interceptor.UserToken;

@Controller
public class SearchController {

	@RequestMapping(value = "/doctor/search", method = RequestMethod.GET)
	public ModelAndView searchPage(HttpSession session) {

		ModelAndView mav = new ModelAndView("searchpage");
		UserToken userToken = (UserToken) session.getAttribute("userToken");
		mav.addObject("docid", userToken.getUserId());
		
		return mav;
	}

	@RequestMapping(value = "/doctor/search/{pNirc}", method = RequestMethod.POST)
	@ResponseJSONP
	public String search(@PathVariable String pNirc, HttpSession session) {

		return new SearchApp().doAction(pNirc, session);
	}
	
	@RequestMapping(value = "/patient/exist/{pNirc}", method = RequestMethod.GET)
	@ResponseJSONP
	public String checkPatient(@PathVariable String pNirc) {
		
		return new SearchApp().doAction(pNirc);
	}
}
