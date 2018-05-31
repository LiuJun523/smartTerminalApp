package com.iss.smartterminal.pojo;

public class LoginPojo{

	private String nirc;
	private String password;
	
	public String getNirc() {
		return nirc;
	}
	public void setNirc(String nirc) {
		this.nirc = nirc;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginPojo [nirc=" + nirc + ", password=" + password + "]";
	}
}
