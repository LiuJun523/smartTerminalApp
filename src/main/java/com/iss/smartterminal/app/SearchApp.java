package com.iss.smartterminal.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.dao.PatientDao;
import com.iss.smartterminal.dao.RelationshipDao;
import com.iss.smartterminal.dao.impl.PatientDaoImpl;
import com.iss.smartterminal.dao.impl.RelationshipDaoImpl;
import com.iss.smartterminal.interceptor.UserToken;
import com.iss.smartterminal.model.Patient;
import com.iss.smartterminal.model.Relationship;
import com.iss.smartterminal.utils.StringUtil;

public class SearchApp {

	PatientDao patientDao = new PatientDaoImpl();
	RelationshipDao relationshipDao = new RelationshipDaoImpl();

	public String doAction(String pNirc) {

		JSONObject jo = new JSONObject();
		if (StringUtil.isEmpty(pNirc)) {
			jo.put("exist", 0);
		} else {
			List<Patient> patients = patientDao.listByNirc(pNirc);

			if (patients != null && patients.size() > 0) {
				jo.put("exist", 1);
			} else {
				jo.put("exist", 0);
			}
		}

		return jo.toJSONString();
	}

	public String doAction(String pNirc, HttpSession session) {

		UserToken userToken = (UserToken) session.getAttribute("userToken");
		List<PatientsWithRelationship> pwrs = new ArrayList<>();

		List<Patient> patients = patientDao.listByNirc(pNirc);
		List<Relationship> relationships = relationshipDao.listByDoctorid(userToken.getUserId());
		Set<String> subPids = getPidFromRelationships(relationships);

		if (patients != null && patients.size() > 0) {
			for (Patient p : patients) {
				String gender = p.getGender() == 1 ? "Male" : "Female";
				PatientsWithRelationship pwr = new PatientsWithRelationship(p.getId(), p.getUserName(), p.getNirc(),
						gender, subPids.contains(p.getId()));
				pwrs.add(pwr);
			}
		}

		return JSONArray.toJSONString(pwrs);
	}

	private Set<String> getPidFromRelationships(List<Relationship> relationships) {

		Set<String> pids = new HashSet<>();

		relationships.forEach(r -> pids.add(r.getPatid()));

		return pids;
	}

	class PatientsWithRelationship {

		private String pId;
		private String pName;
		private String pNirc;
		private String gender;
		private boolean isSubscribe;

		public PatientsWithRelationship(String patientId, String patientName, String nirc, String gender,
				boolean isSubscribe) {
			super();
			this.pId = patientId;
			this.pName = patientName;
			this.pNirc = nirc;
			this.gender = gender;
			this.isSubscribe = isSubscribe;
		}

		public String getpId() {
			return pId;
		}

		public void setpId(String pId) {
			this.pId = pId;
		}

		public String getpName() {
			return pName;
		}

		public void setpName(String pName) {
			this.pName = pName;
		}

		public String getpNirc() {
			return pNirc;
		}

		public void setpNirc(String pNirc) {
			this.pNirc = pNirc;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public boolean isSubscribe() {
			return isSubscribe;
		}

		public void setSubscribe(boolean isSubscribe) {
			this.isSubscribe = isSubscribe;
		}

	}

}
