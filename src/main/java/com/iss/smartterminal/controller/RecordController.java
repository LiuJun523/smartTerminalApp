package com.iss.smartterminal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.iss.smartterminal.app.RecordAddApp;
import com.iss.smartterminal.app.RecordApp;
import com.iss.smartterminal.pojo.CommentPojo;

@Controller
public class RecordController {

	@RequestMapping(value = "/record/list", method = RequestMethod.POST)
	@ResponseJSONP
	public String list(@RequestParam("minTime") long minTime, HttpSession session) {

		return new RecordApp().doAction(minTime, session);
	}

	@RequestMapping(value = "/record/comment", method = RequestMethod.POST)
	@ResponseJSONP
	public String comment(@ModelAttribute("cPojo") CommentPojo cPojo) {

		return new RecordApp().doAction(cPojo);
	}

	@RequestMapping(value = "/record/add", method = RequestMethod.GET)
	@ResponseJSONP
	public String add(@RequestParam("imgUrl") String imgUrl) {

		//imgUrl=https://s3-ap-southeast-1.amazonaws.com/smart-terminal-project-result/PNIRC2_30_1527434638.jpg
		return new RecordAddApp().doAction(imgUrl);
	}
}
