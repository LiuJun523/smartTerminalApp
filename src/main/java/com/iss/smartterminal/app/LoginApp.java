package com.iss.smartterminal.app;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.dao.DoctorDao;
import com.iss.smartterminal.dao.impl.DoctorDaoImpl;
import com.iss.smartterminal.interceptor.UserToken;
import com.iss.smartterminal.model.Doctor;
import com.iss.smartterminal.pojo.LoginPojo;
import com.iss.smartterminal.utils.MD5Util;
import com.iss.smartterminal.utils.StringUtil;

public class LoginApp {

	DoctorDao doctorDao = new DoctorDaoImpl();

	public String doAction(LoginPojo lPojo, HttpSession session) {

		JSONObject jo = new JSONObject();
		int state = -1;
		String msg = "";

		Doctor docPojo = new Doctor();
		docPojo.setNirc(lPojo.getNirc());
		docPojo.setPassword(MD5Util.encryp(lPojo.getPassword()));

		Doctor doctor = doctorDao.getDoctByNircPwd(docPojo);
		if (doctor == null || StringUtil.isEmpty(doctor.getId())) {
			state = 0;
			msg = "login failed";
		} else {
			jo.put("docid", doctor.getId());
			state = 1;
			msg = "login success";
			session.setAttribute("userToken", new UserToken(doctor.getId(), doctor.getUserName()));
			session.setMaxInactiveInterval(5 * 60);
			// TODO 检查关注列表，有关注但无 feed 做拉取
		}
		jo.put("state", state);
		jo.put("msg", msg);

		return jo.toJSONString();
	}

	// public ModelAndView doAction(LoginPojo lPojo) {
	//
	// ModelAndView mav = null;
	//
	// Doctor docPojo = new Doctor();
	// docPojo.setNirc(lPojo.getNirc());
	// docPojo.setPassword(MD5Util.encryp(lPojo.getPassword()));
	//
	// Doctor doctor = doctorDao.getDoctByNircPwd(docPojo);
	// if (doctor == null || StringUtil.isEmpty(doctor.getId())) {
	// mav = new ModelAndView("index");
	// mav.addObject("state", 0);
	// mav.addObject("msg", "login failed");
	// } else {
	// mav = new ModelAndView("redirect:/doctor/info/" + doctor.getId());
	// doctor.setPassword("");
	// mav.addObject("doctor", doctor);
	//
	// // TODO 检查关注列表，有关注但无 feed 做拉取
	// }
	//
	// return mav;
	// }
}
