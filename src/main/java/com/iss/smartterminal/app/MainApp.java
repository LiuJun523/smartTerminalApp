package com.iss.smartterminal.app;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.iss.smartterminal.dao.DoctorDao;
import com.iss.smartterminal.dao.impl.DoctorDaoImpl;
import com.iss.smartterminal.interceptor.UserToken;
import com.iss.smartterminal.model.Doctor;

public class MainApp {

	DoctorDao doctorDao = new DoctorDaoImpl();

	public ModelAndView doAction(String view, HttpSession session) {

		UserToken userToken = (UserToken) session.getAttribute("userToken");
		ModelAndView mav = new ModelAndView(view);
		Doctor doctor = doctorDao.getDoctorById(userToken.getUserId());
		mav.addObject("doctor", doctor);
		mav.addObject("docid", userToken.getUserId());
		mav.addObject("docName", userToken.getUserName());

		return mav;
	}
}
