package com.qq.process.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.process.ClientProcessI;

public class FriendsrefreshProcess implements ClientProcessI{

	@Override
	public void Process(Socket sk, Request rq, Response rp) {
		
		Request rq1 = new Request();
		rq1.setRequestname("Friends");
		rq1.setUser(Information.getU());
		rq1.setQq(Information.getU().getQq());
		
		try {
			OutputStream os = sk.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(rq1);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
