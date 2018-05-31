package com.iss.smartterminal.dao;

import java.util.List;
import java.util.Map;

import com.iss.smartterminal.model.Record;

public interface RecordDao {

	public int add(Record record);

	public int addComment(Record record);

	public List<Record> getByRecordids(List<String> recordIds, long addTime, int limit);

	public List<Record> getByPatientids(List<String> patientIds, long addTime, int limit);

	public Map<String, Double> getIdandAtimeByPatientids(List<String> patientIds, long addTime, int limit);
}
