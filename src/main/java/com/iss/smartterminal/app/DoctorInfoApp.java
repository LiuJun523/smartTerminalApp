package com.iss.smartterminal.app;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.dao.DoctorDao;
import com.iss.smartterminal.dao.impl.DoctorDaoImpl;
import com.iss.smartterminal.interceptor.UserToken;
import com.iss.smartterminal.model.Doctor;
import com.iss.smartterminal.pojo.DoctorPojo;

public class DoctorInfoApp {

	DoctorDao doctorDao = new DoctorDaoImpl();

	public ModelAndView doAction(String docid, String view, HttpSession session) {

		UserToken userToken = (UserToken) session.getAttribute("userToken");
		ModelAndView mav = new ModelAndView(view);
		Doctor doctor = doctorDao.getDoctorById(docid);
		if (doctor != null) {
			doctor.setPassword("");
			mav.addObject("doctor", doctor);
			mav.addObject("docid", userToken.getUserId());
			mav.addObject("docName", userToken.getUserName());
		}

		return mav;
	}

	public String doAction(String docid, DoctorPojo docPojo) {

		JSONObject jo = new JSONObject();
		int state = -1;
		String msg = "";

		Doctor doctor = doctorDao.getDoctorById(docid);
		if (doctor != null) {
			doctor.setUpdateTime(new Date());
			doctor.setPhoneNumber(docPojo.getPhoneNumber());
			doctor.setHospital(docPojo.getHospital());
			doctor.setDepartment(docPojo.getDepartment());
			if (doctorDao.update(doctor) > 0) {
				state = 1;
				msg = "update success";
			} else {
				state = 0;
				msg = "update failed";
			}

		} else {
			state = 0;
			msg = "get doctor failed";
		}

		jo.put("state", state);
		jo.put("msg", msg);
		jo.put("docid", docid);

		return jo.toJSONString();
	}
}
