package com.qq.process.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JList;

import com.qq.Window.WindowQQ;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.bean.User;
import com.qq.process.ClientProcessI;

public class GetFriendsProcess implements ClientProcessI{

	@Override
	public void Process(Socket sk, Request rq, Response rp) {
		
		ArrayList<User> li = (ArrayList<User>) rp.getli();
		User[] u = new User[li.size()];
		for (int i = 0; i < li.size(); i++) {
			u[i]=li.get(i);
		}
		WindowQQ.l.setListData(u);
		WindowQQ.l.repaint();
	}

}
