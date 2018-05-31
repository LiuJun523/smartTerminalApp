package com.iss.smartterminal.model;

import java.util.Date;

import com.iss.smartterminal.utils.DateUtil;

public class Patient extends BaseModel {

	private String nirc;
	private String userName;
	private int gender;
	private Date birthday;
	private String phoneNumber;
	private String docid;

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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	@Override
	public String toString() {
		return "Patient [nirc=" + nirc + ", userName=" + userName + ", gender=" + gender + ", birthday="
				+ DateUtil.convertDate(updateTime) + ", phoneNumber=" + phoneNumber + ", docid=" + docid + ", id=" + id
				+ ", addTime=" + DateUtil.convertDate(addTime) + ", updateTime=" + DateUtil.convertDate(updateTime)
				+ ", delFlag=" + delFlag + ", exLong=" + exLong + ", exString=" + exString + "]";
	}

}
