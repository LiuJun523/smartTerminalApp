package com.iss.smartterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.dao.impl.UserDaoImpl;
import com.iss.smartterminal.entity.User;
import com.iss.smartterminal.pojo.RegisterPojo;
import com.iss.smartterminal.utils.UUIDUtil;

@Controller
public class RegistrationController {

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser() {
		return new ModelAndView("register");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseJSONP
	public String register(@ModelAttribute("rPojo") RegisterPojo rPojo) {
		
		System.out.println("insert user:" + rPojo);
		JSONObject jo = new JSONObject();
		jo.put("rpojo", rPojo);
		jo.put("status", "success");
		UserDaoImpl daoimpl = new UserDaoImpl();
		
		User user = new User(UUIDUtil.getUUID(), rPojo.getUserName(), rPojo.getPassword());
		jo.put("result", daoimpl.register(user));
		System.out.println("reg");
		return jo.toJSONString();
	}
}
