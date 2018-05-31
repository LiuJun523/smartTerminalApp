package com.iss.smartterminal.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.dao.DoctorDao;
import com.iss.smartterminal.dao.impl.DoctorDaoImpl;
import com.iss.smartterminal.model.Doctor;
import com.iss.smartterminal.pojo.RegisterPojo;
import com.iss.smartterminal.utils.IDUtil;
import com.iss.smartterminal.utils.MD5Util;

public class RegisterApp {

	DoctorDao doctorDao = new DoctorDaoImpl();

	public String doAction(RegisterPojo rPojo) {

		JSONObject jo = new JSONObject();

		Doctor doctor = generateDoctor(rPojo);
		int isExist = doctorDao.isDoctorExist(doctor);

		String msg = "";
		int state = 0;

		switch (isExist) {
		case 0:
			Map<String, Object> resMap = insertDoctor(doctor);
			state = (int) resMap.get("state");
			msg = (String) resMap.get("msg");
			break;
		case 1:
			msg = "doctor " + doctor.getNirc() + " already exist.";
			break;
		case -1:
			msg = "db exception.";
			break;
		}
		jo.put("state", state);
		jo.put("msg", msg);

		return jo.toJSONString();
	}

	private Doctor generateDoctor(RegisterPojo rPojo) {

		Doctor doctor = new Doctor();
		doctor.setId(IDUtil.generateID());
		doctor.setAddTime(new Date().getTime());
		doctor.setUpdateTime(new Date());
		doctor.setEmail(rPojo.getEmail());
		doctor.setUserName(rPojo.getUserName());
		doctor.setPassword(MD5Util.encryp(rPojo.getPassword()));
		doctor.setNirc(rPojo.getNirc());
		return doctor;
	}

	private Map<String, Object> insertDoctor(Doctor doctor) {

		int state = doctorDao.add(doctor);
		String msg = "add doctor " + doctor.getNirc() + " failed.";
		if (state > 0) {
			msg = "add doctor " + doctor.getNirc() + " success.";
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("state", state);
		resultMap.put("msg", msg);

		return resultMap;
	}
}
