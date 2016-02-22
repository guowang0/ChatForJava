package com.qq.bean;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.qq.Window.WindowChat;

public class Information {

	private static Socket sk;
	private static User u;
	//li用来存放当前用户的聊天窗口
	public static List<WindowChat> li = new ArrayList<WindowChat>();

	public static User getU() {
		return u;
	}

	public static void setU(User u) {
		Information.u = u;
	}

	public static Socket getSk() {
		return sk;
	}

	public static void setSk(Socket sk) {
		Information.sk = sk;
	}
}
