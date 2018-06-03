package com.iss.smartterminal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iss.smartterminal.awshelper.RDSHelper;
import com.iss.smartterminal.dao.PatientDao;
import com.iss.smartterminal.model.Patient;
import com.iss.smartterminal.utils.DateUtil;

public class PatientDaoImpl implements PatientDao {

	@Override
	public int add(Patient patient) {

		String sql = String.format(
				"insert into patient(id,addTime,delFlag,updateTime,nirc,userName,gender,birthday,phoneNumber,docid,exLong,exString) values('%s', %d, %d, '%s', '%s', '%s', %d, '%s', '%s', '%s', %d, '%s')",
				patient.getId(), patient.getAddTime(), 0, DateUtil.convertDate(patient.getUpdateTime()),
				patient.getNirc(), patient.getUserName(), patient.getGender(),
				DateUtil.convertDate(patient.getBirthday()), patient.getPhoneNumber(), patient.getDocid(),
				patient.getExLong(), patient.getExString());

		return new BaseImpl().update(sql);
	}

	@Override
	public int isPatientExist(String nirc) {

		String sql = "SELECT id FROM patient where delFlag=0 and nirc='" + nirc + "'";

		return new BaseImpl().exist(sql);
	}

	@Override
	public List<Patient> listByNirc(String nirc) {

		List<Patient> patients = new ArrayList<>();
		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			String sql = "SELECT * FROM patient where delFlag=0 and nirc like '%" + nirc + "%'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Patient patient = getPatientFromRS(rs);
				patients.add(patient);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return patients;
	}

	@Override
	public List<Patient> listByDocid(String docid) {

		List<Patient> patients = new ArrayList<>();
		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			String sql = "SELECT * FROM patient where delFlag=0 and docid='" + docid + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Patient patient = getPatientFromRS(rs);
				patients.add(patient);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return patients;
	}

	@Override
	public Patient getPatientById(String patientId) {

		Patient patient = null;
		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			String sql = "SELECT * FROM patient where delFlag=0 and id='" + patientId + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				patient = getPatientFromRS(rs);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return patient;
	}

	@Override
	public List<Patient> listByPatids(List<String> patIds) {

		String ids = "";
		for (String id : patIds) {
			ids += "'" + id + "',";
		}
		ids = ids.substring(0, ids.length() - 1);
		List<Patient> patients = new ArrayList<>();
		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			String sql = "SELECT * FROM patient where delFlag=0 and id in (" + ids + ")";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Patient patient = getPatientFromRS(rs);
				patients.add(patient);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	private Patient getPatientFromRS(ResultSet rs) throws SQLException {

		Patient patient = new Patient();
		patient.setId(rs.getString("id"));
		patient.setAddTime(rs.getLong("addTime"));
		patient.setDelFlag(rs.getInt("delFlag"));
		patient.setUpdateTime(rs.getDate("updateTime"));
		patient.setExLong(rs.getLong("exLong"));
		patient.setExString(rs.getString("exString"));
		patient.setNirc(rs.getString("nirc"));
		patient.setUserName(rs.getString("userName"));
		patient.setGender(rs.getInt("gender"));
		patient.setBirthday(rs.getDate("birthday"));
		patient.setPhoneNumber(rs.getString("phoneNumber"));
		patient.setDocid(rs.getString("docid"));
		return patient;
	}

}
