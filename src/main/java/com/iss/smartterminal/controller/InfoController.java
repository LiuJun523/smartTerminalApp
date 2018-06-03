package com.iss.smartterminal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.app.DoctorInfoApp;
import com.iss.smartterminal.pojo.DoctorPojo;

@Controller
public class InfoController {

	@RequestMapping(value = "/doctor/info/{docid}", method = RequestMethod.GET)
	public ModelAndView info(@PathVariable String docid, HttpSession session) {

		return new DoctorInfoApp().doAction(docid, "personal", session);
	}

	@RequestMapping(value = "/doctor/info/{docid}", method = RequestMethod.POST)
	@ResponseJSONP
	public String modifyInfo(@PathVariable String docid, @ModelAttribute("docPojo") DoctorPojo docPojo) {

		return new DoctorInfoApp().doAction(docid, docPojo);
	}
}
