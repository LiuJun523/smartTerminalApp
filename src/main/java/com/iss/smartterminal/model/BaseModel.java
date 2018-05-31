package com.iss.smartterminal.model;

import java.util.Date;

public class BaseModel {
	
	String id;
	long addTime;
	Date updateTime;
	int delFlag;
	long exLong;
	String exString;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getAddTime() {
		return addTime;
	}
	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public long getExLong() {
		return exLong;
	}
	public void setExLong(long exLong) {
		this.exLong = exLong;
	}
	public String getExString() {
		return exString;
	}
	public void setExString(String exString) {
		this.exString = exString;
	}
}
