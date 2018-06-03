package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.app.PatientApp;
import com.iss.smartterminal.pojo.PatientPojo;

@Controller
public class PatientController {

	@RequestMapping(value = "/doctor/patient", method = RequestMethod.GET)
	public ModelAndView addPatientPage(@RequestParam("docid") String docid) {
		
		ModelAndView mav = new ModelAndView("add");
		mav.addObject("docid", docid);
		
		return mav;
	}

	@RequestMapping(value = "/doctor/patient", method = RequestMethod.POST)
	@ResponseJSONP
	public String addPatient(@ModelAttribute("pPojo") PatientPojo pPojo) {
		
		return new PatientApp().doAction(pPojo);
	}
	
	@RequestMapping(value = "/doctor/{docid}/patientlist", method = RequestMethod.GET)
	public ModelAndView patientList(@PathVariable String docid) {
		
		return new PatientApp().doAction(docid, "mypatients");
	}
}
