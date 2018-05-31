package com.iss.smartterminal.app;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.dao.PatientDao;
import com.iss.smartterminal.dao.impl.PatientDaoImpl;
import com.iss.smartterminal.model.Patient;
import com.iss.smartterminal.pojo.PatientPojo;
import com.iss.smartterminal.utils.IDUtil;

public class PatientApp {

	PatientDao patientDao = new PatientDaoImpl();

	public String doAction(PatientPojo pPojo) {

		JSONObject jo = new JSONObject();
		int state = 0;
		String msg = "";

		if (patientDao.isPatientExist(pPojo.getNirc()) > 0) {
			msg = "patient existed.";
		} else {
			Patient patient = getPatientFromPojo(pPojo);
			if (patientDao.add(patient) > 0) {
				state = 1;
				msg = "add patient success.";
			} else {
				msg = "add patient failed.";
			}
		}
		jo.put("state", state);
		jo.put("msg", msg);

		return jo.toJSONString();
	}

	public String doAction(String docid) {

		List<Patient> patients = patientDao.listByDocid(docid);

		JSONObject jo = new JSONObject();
		jo.put("patients", patients);

		return jo.toJSONString();
	}

	private Patient getPatientFromPojo(PatientPojo pPojo) {

		Patient patient = new Patient();
		patient.setId(IDUtil.generateID());
		patient.setAddTime(new Date().getTime());
		patient.setDelFlag(0);
		patient.setUpdateTime(new Date());
		patient.setNirc(pPojo.getNirc());
		patient.setUserName(pPojo.getUserName());
		patient.setGender(pPojo.getGender());
		patient.setBirthday(pPojo.getBirthday());
		patient.setPhoneNumber(pPojo.getPhoneNumber());
		patient.setDocid(pPojo.getDocid());
		return patient;
	}
}
