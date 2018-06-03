package com.iss.smartterminal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iss.smartterminal.awshelper.RDSHelper;
import com.iss.smartterminal.dao.DoctorDao;
import com.iss.smartterminal.model.Doctor;
import com.iss.smartterminal.utils.DateUtil;

public class DoctorDaoImpl implements DoctorDao {

	@Override
	public int add(Doctor doctor) {

		String sql = String.format(
				"insert into doctor(id,addTime,delFlag,updateTime,nirc,userName,password,email,exLong,exString,hospital,department,phoneNumber) values('%s', %d, %d, '%s', '%s', '%s', '%s', '%s', %d, '%s', '%s', '%s', '%s')",
				doctor.getId(), doctor.getAddTime(), 0, DateUtil.convertDate(doctor.getUpdateTime()), doctor.getNirc(),
				doctor.getUserName(), doctor.getPassword(), doctor.getEmail(), doctor.getExLong(), doctor.getExString(),
				doctor.getHospital(), doctor.getDepartment(), doctor.getPhoneNumber());

		return new BaseImpl().update(sql);
	}

	@Override
	public int update(Doctor doctor) {

		String sql = String.format(
				"update doctor set updateTime='%s', phoneNumber='%s', hospital='%s', department='%s' where id='%s';",
				DateUtil.convertDate(doctor.getUpdateTime()), doctor.getPhoneNumber(), doctor.getHospital(),
				doctor.getDepartment(), doctor.getId());

		return new BaseImpl().update(sql);
	}

	@Override
	public int isDoctorExist(Doctor doctor) {

		String sql = "SELECT id FROM doctor where delFlag=0 and nirc='" + doctor.getNirc() + "'";

		return new BaseImpl().exist(sql);
	}

	@Override
	public Doctor getDoctorById(String id) {

		Doctor doctor = null;
		String sql = "SELECT * FROM doctor where delFlag=0 and id='" + id + "' limit 1";

		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				doctor = getDoctorFromRS(rs);
				break;
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctor;
	}

	@Override
	public Doctor getDoctByNircPwd(Doctor doctor) {

		Doctor resdoc = null;
		String sql = String.format("SELECT * FROM doctor where delFlag=0 and nirc='%s' and password='%s' limit 1",
				doctor.getNirc(), doctor.getPassword());

		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				resdoc = getDoctorFromRS(rs);
				break;
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resdoc;
	}

	private Doctor getDoctorFromRS(ResultSet rs) throws SQLException {
		Doctor doctor = new Doctor();
		doctor.setId(rs.getString("id"));
		doctor.setAddTime(rs.getLong("addTime"));
		doctor.setDelFlag(rs.getInt("delFlag"));
		doctor.setUpdateTime(rs.getDate("updateTime"));
		doctor.setExLong(rs.getLong("exLong"));
		doctor.setExString(rs.getString("exString"));
		doctor.setNirc(rs.getString("nirc"));
		doctor.setUserName(rs.getString("userName"));
		doctor.setPassword(rs.getString("password"));
		doctor.setEmail(rs.getString("email"));
		doctor.setHospital(rs.getString("hospital"));
		doctor.setDepartment(rs.getString("department"));
		doctor.setPhoneNumber(rs.getString("phoneNumber"));
		return doctor;
	}

}
