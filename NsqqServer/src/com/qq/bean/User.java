package com.qq.bean;

import java.io.Serializable;

public class User implements Serializable {

	private String nick;
	private String password;
	private String qq;
	private int status;

	@Override
	public String toString() {
		if (this.status == 0)
			return nick + "(" + qq + ")   " + "¿Îœﬂ";
		else
			return nick + "(" + qq + ")   " + "‘⁄œﬂ";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
}
