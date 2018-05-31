package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.app.SearchApp;

@Controller
public class SearchController {

	@RequestMapping(value = "/doctor/search", method = RequestMethod.GET)
	public ModelAndView searchPage(@RequestParam("docId") String docId) {

		// TODO search page
		ModelAndView mav = new ModelAndView("searchpage");
		mav.addObject("docId", docId);
		return mav;
	}

	@RequestMapping(value = "/doctor/search/{pNirc}", method = RequestMethod.GET)
	@ResponseJSONP
	public String search(@PathVariable String pNirc, @RequestParam("docid") String docId) {

		return new SearchApp().doAction(pNirc, docId);
	}
}
