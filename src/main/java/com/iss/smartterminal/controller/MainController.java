package com.iss.smartterminal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iss.smartterminal.app.MainApp;

@Controller
public class MainController {

	@RequestMapping(value = "/doctor/main", method = RequestMethod.GET)
	public ModelAndView registerPage(HttpSession session) {

		return new MainApp().doAction("main", session);
	}
}
