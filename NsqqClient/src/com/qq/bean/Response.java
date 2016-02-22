package com.qq.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Response implements Serializable{
	
	public static final int LOGIN_OK = 100;
	public static final int LOGIN_NAME = 101;
	public static final int LOGIN_PASSWOARD = 102;
	public static final int ADDFRIEND_OK = 200;
	public static final int ADDFRIEND_FAIL = 201;
	
	private String Responsename;
	private int responsecode;
	private User user;
	private String message;
	private String qq;
	private List<User> li;
	
	
	public List<User> getli() {
		return li;
	}
	public void setli(List<User> li) {
		this.li = li;
	}
	public String getResponsename() {
		return Responsename;
	}
	public void setResponsename(String responsename) {
		Responsename = responsename;
	}
	public User getUser() {
		return user;
	}
	public int getResponsecode() {
		return responsecode;
	}
	public void setResponsecode(int responsecode) {
		this.responsecode = responsecode;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}	
}
