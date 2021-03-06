package com.iss.smartterminal.pojo;

public class RegisterPojo {

	private String nirc;
	private String userName;
	private String password;
	private String email;
	
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
	
	@Override
	public String toString() {
		return "RegisterPojo [nirc=" + nirc + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ "]";
	}
}
