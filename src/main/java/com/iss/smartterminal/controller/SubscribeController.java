package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.app.SubscribeApp;

@Controller
public class SubscribeController {

	@RequestMapping(value = "/doctor/subscribe", method = RequestMethod.POST)
	@ResponseJSONP
	public String subscribe(@RequestParam("docid") String docid, @RequestParam("patid") String patid) {

		return new SubscribeApp().doAction(docid, patid, true);
	}

	@RequestMapping(value = "/doctor/unsubscribe", method = RequestMethod.POST)
	@ResponseJSONP
	public String unsubscribe(@RequestParam("docid") String docid, @RequestParam("patid") String patid) {

		return new SubscribeApp().doAction(docid, patid, false);
	}
}
