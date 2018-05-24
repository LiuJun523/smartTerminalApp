package com.iss.smartterminal.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.entity.User;

@Controller
public class HelloWorldController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String sayHello(ModelMap model) {
		
		System.out.println("in");
		model.addAttribute("message", "Welcome from Spring MVC");
		
		return "index";
	}
	
	@RequestMapping(value="/helloAgain", method=RequestMethod.GET)
	public String sayHelloAgain(ModelMap model) {
		model.addAttribute("message", "Welcome Again from Spring MVC");
		return "index";
	}
	
	@RequestMapping(value="/testbody")
	@ResponseJSONP
	public String lc(@RequestHeader Map<String, String> headers, @ModelAttribute User user) {
		
//		String name1 = Optional.ofNullable(name).orElse("doesn't get");
		JSONObject jo = new JSONObject();
//		jo.put("testname", name1);
		jo.put("ua", headers);
		return jo.toJSONString();
	}
}
