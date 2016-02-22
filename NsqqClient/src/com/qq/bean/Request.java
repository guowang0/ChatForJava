package com.qq.bean;

import java.io.Serializable;

public class Request implements Serializable{
	
	private String Requestname;
	private User user;
	private String qq;
	private String toqq;
	private String message;
	
	
	public String getRequestname() {
		return Requestname;
	}
	public void setRequestname(String requestname) {
		Requestname = requestname;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getToqq() {
		return toqq;
	}
	public void setToqq(String toqq) {
		this.toqq = toqq;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
