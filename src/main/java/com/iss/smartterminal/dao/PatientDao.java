package com.iss.smartterminal.dao;

import java.util.List;

import com.iss.smartterminal.model.Patient;

public interface PatientDao {

	public int add(Patient patient);

	public List<Patient> listByNirc(String nirc);

	public List<Patient> listByDocid(String docid);
	
	public List<Patient> listByPatids(List<String> patIds);

	public int isPatientExist(String nirc);

	public Patient getPatientById(String patientId);
}