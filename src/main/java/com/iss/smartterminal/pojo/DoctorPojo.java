package com.iss.smartterminal.pojo;

public class DoctorPojo {

	private String email;
	private String phoneNumber;
	private String hospital;
	private String department;

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
		return "DoctorPojo [email=" + email + ", phoneNumber=" + phoneNumber + ", hospital=" + hospital
				+ ", department=" + department + "]";
	}
	
}
