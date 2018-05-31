package com.iss.smartterminal.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.entity.User;
import com.iss.smartterminal.extra.DemoDynamoDB;
import com.iss.smartterminal.extra.DemoRedis;
import com.iss.smartterminal.extra.DemoRegisterPojo;
import com.iss.smartterminal.extra.UserDaoImpl;
import com.iss.smartterminal.utils.IDUtil;

import redis.clients.jedis.Tuple;

@Controller
public class TestController {

	@RequestMapping(value = "/test/testCache")
	@ResponseJSONP
	public String testCache() {
		
		JSONObject jo = new JSONObject();
		DemoRedis.init();
		DemoRedis.add();
		Set<Tuple> tuples = DemoRedis.get();

		String result = "";
		for (Tuple t : tuples) {
			result += "ele:" + t.getElement() + " | score:" + t.getScore() + ";";
		}
		jo.put("result", result);

		return jo.toJSONString();
	}
	
	@RequestMapping(value = "/test/testDyDB")
	@ResponseJSONP
	public String testDyDB() {
		
		JSONObject jo = DemoDynamoDB.createItems();
		return jo.toJSONString();
	}
	
	@RequestMapping(value="/test/", method=RequestMethod.GET)
	public String sayHello() {
		
		return "index";
	}
	
	@RequestMapping(value="/test/helloAgain", method=RequestMethod.GET)
	public String sayHelloAgain(ModelMap model) {
		model.addAttribute("message", "Welcome Again from Spring MVC");
		return "index";
	}
	
	@RequestMapping(value="/test/testbody")
	@ResponseJSONP
	public String lc(@RequestHeader Map<String, String> headers, @ModelAttribute User user) {
		
//		String name1 = Optional.ofNullable(name).orElse("doesn't get");
		JSONObject jo = new JSONObject();
//		jo.put("testname", name1);
		jo.put("ua", headers);
		return jo.toJSONString();
	}
	
	@RequestMapping(value = "/test/newUser", method = RequestMethod.GET)
	public ModelAndView newUser() {
		
		return new ModelAndView("register");
	}

	@RequestMapping(value = "/test/register", method = RequestMethod.POST)
	@ResponseJSONP
	public String register(@ModelAttribute("rPojo") DemoRegisterPojo rPojo) {
		
		System.out.println("insert user:" + rPojo);
		JSONObject jo = new JSONObject();
		jo.put("rpojo", rPojo);
		jo.put("status", "success");
		UserDaoImpl daoimpl = new UserDaoImpl();
		
		User user = new User(IDUtil.generateID(), rPojo.getUserName(), rPojo.getPassword());
		jo.put("result", daoimpl.register(user));
		System.out.println("reg");
		return jo.toJSONString();
	}
	
	@RequestMapping("/test/user")
	public ModelAndView getIndex() {
		
		ModelAndView mav= new ModelAndView(); 
		User user = new User("a", "2", "q");
		mav.addObject("user", user);
	    mav.addObject("name","this name");  
	    mav.setViewName("patients");  
	    return mav;  
	}
}
