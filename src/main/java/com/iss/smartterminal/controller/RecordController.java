package com.iss.smartterminal.controller;

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

	@RequestMapping(value = "/record/list", method = RequestMethod.GET)
	@ResponseJSONP
	public String list(@RequestParam("docid") String docid, @RequestParam("page") int page) {

		return new RecordApp().doAction(docid, page);
	}

	@RequestMapping(value = "/record/comment", method = RequestMethod.POST)
	@ResponseJSONP
	public String comment(@ModelAttribute("cPojo") CommentPojo cPojo) {

		return new RecordApp().doAction(cPojo);
	}

	@RequestMapping(value = "/record/add", method = RequestMethod.POST)
	@ResponseJSONP
	public String add(@RequestParam("imgUrl") String imgUrl) {

		return new RecordAddApp().doAction(imgUrl);
	}
}
