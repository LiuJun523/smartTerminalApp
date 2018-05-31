package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iss.smartterminal.app.DoctorInfoApp;
import com.iss.smartterminal.pojo.DoctorPojo;

@Controller
public class InfoController {

	@RequestMapping(value = "/doctor/info/{docid}", method = RequestMethod.GET)
	public ModelAndView info(@PathVariable String docid) {

		return new DoctorInfoApp().doAction(docid, "personal");
	}

	@RequestMapping(value = "/doctor/info/{docid}", method = RequestMethod.POST)
	public ModelAndView modifyInfo(@PathVariable String docid, @ModelAttribute("docPojo") DoctorPojo docPojo) {

		return new DoctorInfoApp().doAction(docid, docPojo, "personal");
	}
}
