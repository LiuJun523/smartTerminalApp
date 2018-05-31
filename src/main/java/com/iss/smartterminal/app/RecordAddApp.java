package com.iss.smartterminal.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.awshelper.ECacheHelper;
import com.iss.smartterminal.dao.PatientDao;
import com.iss.smartterminal.dao.RecordDao;
import com.iss.smartterminal.dao.RelationshipDao;
import com.iss.smartterminal.dao.impl.PatientDaoImpl;
import com.iss.smartterminal.dao.impl.RecordDaoImpl;
import com.iss.smartterminal.dao.impl.RelationshipDaoImpl;
import com.iss.smartterminal.model.Patient;
import com.iss.smartterminal.model.Record;
import com.iss.smartterminal.model.Relationship;
import com.iss.smartterminal.utils.IDUtil;

public class RecordAddApp {

	RecordDao recordDao = new RecordDaoImpl();
	PatientDao patientDao = new PatientDaoImpl();
	RelationshipDao relationshipDao = new RelationshipDaoImpl();

	public String doAction(String imgUrl) {

		JSONObject jo = new JSONObject();
		int state = -1;
		String msg = "";

		String patientId = imgUrl.split("/")[imgUrl.split("/").length - 1].split("_")[0];
		Patient patient = patientDao.getPatientById(patientId);

		Record record = generateRecord(imgUrl, patient);
		if (recordDao.add(record) > 0) {
			List<Relationship> relationships = relationshipDao.listByPatientid(patientId);
			relationships.forEach(r -> {
				ECacheHelper.getJedis().zadd(r.getDocid(), record.getAddTime(), record.getRecordId());
				ECacheHelper.rmExtra(r.getDocid());
			});
			state = 1;
			msg = "add record success";
		} else {
			state = 0;
			msg = "add record failed";
		}

		jo.put("state", state);
		jo.put("msg", msg);

		return jo.toJSONString();
	}

	private Record generateRecord(String imgUrl, Patient patient) {

		Record record = new Record();
		record.setRecordId(IDUtil.generateID());
		record.setAddTime(new Date().getTime());
		record.setUpdateTime(new Date().getTime());
		record.setComments(new ArrayList<>());
		record.setImgUrl(imgUrl);
		record.setPatientId(patient.getId());
		record.setPatientName(patient.getUserName());

		return record;
	}

}
