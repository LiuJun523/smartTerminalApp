package com.iss.smartterminal.app;

import java.util.Date;

import org.springframework.web.servlet.ModelAndView;

import com.iss.smartterminal.dao.DoctorDao;
import com.iss.smartterminal.dao.impl.DoctorDaoImpl;
import com.iss.smartterminal.model.Doctor;
import com.iss.smartterminal.pojo.DoctorPojo;

public class DoctorInfoApp {

	DoctorDao doctorDao = new DoctorDaoImpl();

	public ModelAndView doAction(String docid, String view) {

		ModelAndView mav = new ModelAndView(view);
		Doctor doctor = doctorDao.getDoctorById(docid);
		if (doctor != null) {
			doctor.setPassword("");
			mav.addObject("doctor", doctor);
		}

		return mav;
	}

	public ModelAndView doAction(String docid, DoctorPojo docPojo, String view) {

		ModelAndView mav = new ModelAndView(view);

		Doctor doctor = doctorDao.getDoctorById(docid);
		if (doctor != null) {
			doctor.setUpdateTime(new Date());
			doctor.setEmail(docPojo.getEmail());
			doctor.setPhoneNumber(docPojo.getPhoneNumber());
			doctor.setHospital(docPojo.getHospital());
			doctor.setDepartment(docPojo.getDepartment());
			doctorDao.update(doctor);
			mav.addObject("doctor", doctor);
		}

		return mav;
	}
}
