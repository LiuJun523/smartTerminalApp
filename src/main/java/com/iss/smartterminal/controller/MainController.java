package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value = "/doctor/main", method = RequestMethod.GET)
	public ModelAndView registerPage(@RequestParam("docid") String docid, @RequestParam("docName") String docName) {

		ModelAndView mav = new ModelAndView("main");
		mav.addObject("docid", docid);
		mav.addObject("docName", docName);

		return mav;
	}
}
