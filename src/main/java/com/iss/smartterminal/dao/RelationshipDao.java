package com.iss.smartterminal.dao;

import java.util.List;

import com.iss.smartterminal.model.Relationship;

public interface RelationshipDao {

	public int subscribe(String doctorId, String patientId);

	public int unsubscribe(String doctorId, String patientId);

	public List<Relationship> listByDoctorid(String doctorId);

	public List<Relationship> listByPatientid(String patientId);

	public int hasSubscribed(String doctorId, String patientId);

}
