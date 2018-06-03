package com.iss.smartterminal.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.dao.PatientDao;
import com.iss.smartterminal.dao.RelationshipDao;
import com.iss.smartterminal.dao.impl.PatientDaoImpl;
import com.iss.smartterminal.dao.impl.RelationshipDaoImpl;
import com.iss.smartterminal.model.Patient;
import com.iss.smartterminal.model.Relationship;
import com.iss.smartterminal.pojo.PatientPojo;
import com.iss.smartterminal.utils.IDUtil;

public class PatientApp {

	PatientDao patientDao = new PatientDaoImpl();
	RelationshipDao relationshipDao = new RelationshipDaoImpl();

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

	public ModelAndView doAction(String docid, String view) {

		ModelAndView mav = new ModelAndView(view);

		List<Relationship> relationships = relationshipDao.listByDoctorid(docid);

		List<String> patIds = new ArrayList<>();
		relationships.forEach(r -> patIds.add(r.getPatid()));
		if (patIds.size() > 0) {
			List<Patient> patients = patientDao.listByPatids(patIds);
			mav.addObject("patients", patients);
		}
		mav.addObject("docid", docid);

		return mav;
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
		patient.setExLong(0);
		patient.setExString("");

		return patient;
	}
}
