package com.iss.smartterminal.model;

import com.iss.smartterminal.utils.DateUtil;

public class Doctor extends BaseModel {

	private String nirc;
	private String userName;
	private String password;
	private String email;
	private String phoneNumber;
	private String hospital;
	private String department;

	public String getNirc() {
		return nirc;
	}

	public void setNirc(String nirc) {
		this.nirc = nirc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Doctor [nirc=" + nirc + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", hospital=" + hospital + ", department=" + department + ", id="
				+ id + ", addTime=" + DateUtil.convertDate(addTime) + ", updateTime=" + DateUtil.convertDate(updateTime) + ", delFlag=" + delFlag + ", exLong="
				+ exLong + ", exString=" + exString + "]";
	}

}
