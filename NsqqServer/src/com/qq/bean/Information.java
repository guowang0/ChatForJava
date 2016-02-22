package com.qq.bean;

import java.io.Serializable;
import java.net.Socket;

public class Information implements Serializable{

	private Socket sk;
	private User u;
	public Socket getSk() {
		return sk;
	}
	public void setSk(Socket sk) {
		this.sk = sk;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}


}
