package com.iss.smartterminal.app;

import org.springframework.web.servlet.ModelAndView;

import com.iss.smartterminal.dao.DoctorDao;
import com.iss.smartterminal.dao.impl.DoctorDaoImpl;
import com.iss.smartterminal.model.Doctor;
import com.iss.smartterminal.pojo.LoginPojo;
import com.iss.smartterminal.utils.MD5Util;
import com.iss.smartterminal.utils.StringUtil;

public class LoginApp {

	DoctorDao doctorDao = new DoctorDaoImpl();

	public ModelAndView doAction(LoginPojo lPojo) {

		ModelAndView mav = null;

		Doctor docPojo = new Doctor();
		docPojo.setNirc(lPojo.getNirc());
		docPojo.setPassword(MD5Util.encryp(lPojo.getPassword()));

		Doctor doctor = doctorDao.getDoctByNircPwd(docPojo);
		if (doctor == null || StringUtil.isEmpty(doctor.getId())) {
			mav = new ModelAndView("index");
			mav.addObject("state", 0);
			mav.addObject("msg", "login failed");
		} else {
			mav = new ModelAndView("redirect:/doctor/info/" + doctor.getId());
			doctor.setPassword("");
			mav.addObject("doctor", doctor);
			
			//TODO 检查关注列表，有关注但无 feed 做拉取
		}

		return mav;
	}
}
