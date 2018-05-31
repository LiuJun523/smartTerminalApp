package com.iss.smartterminal.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.alibaba.fastjson.JSONArray;
import com.iss.smartterminal.dao.PatientDao;
import com.iss.smartterminal.dao.RelationshipDao;
import com.iss.smartterminal.dao.impl.PatientDaoImpl;
import com.iss.smartterminal.dao.impl.RelationshipDaoImpl;
import com.iss.smartterminal.model.Patient;
import com.iss.smartterminal.model.Relationship;

public class SearchApp {

	PatientDao patientDao = new PatientDaoImpl();
	RelationshipDao relationshipDao = new RelationshipDaoImpl();

	public String doAction(String pNirc, String docId) {

		List<PatientsWithRelationship> pwrs = new ArrayList<>();

		List<Patient> patients = patientDao.listByNirc(pNirc);
		List<Relationship> relationships = relationshipDao.listByDoctorid(docId);
		Set<String> subPids = getPidFromRelationships(relationships);

		if (patients != null && patients.size() > 0) {
			for (Patient p : patients) {
				PatientsWithRelationship pwr = new PatientsWithRelationship(p.getId(), p.getUserName(), p.getNirc(),
						subPids.contains(p.getId()));
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

	public static void main(String[] args) {

		String str = new SearchApp().doAction("Q1", "494f2ca5c9014291b4a800ab117098f6");

		System.out.println(str);
	}

	class PatientsWithRelationship {

		private String patientId;
		private String patientName;
		private String nirc;
		private boolean isSubscribe;

		public PatientsWithRelationship(String patientId, String patientName, String nirc, boolean isSubscribe) {
			super();
			this.patientId = patientId;
			this.patientName = patientName;
			this.nirc = nirc;
			this.isSubscribe = isSubscribe;
		}

		public String getPatientId() {
			return patientId;
		}

		public void setPatientId(String patientId) {
			this.patientId = patientId;
		}

		public String getPatientName() {
			return patientName;
		}

		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}

		public String getNirc() {
			return nirc;
		}

		public void setNirc(String nirc) {
			this.nirc = nirc;
		}

		public boolean isSubscribe() {
			return isSubscribe;
		}

		public void setSubscribe(boolean isSubscribe) {
			this.isSubscribe = isSubscribe;
		}
	}
}
