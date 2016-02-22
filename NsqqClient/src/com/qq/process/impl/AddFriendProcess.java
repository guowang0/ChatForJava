package com.qq.process.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.bean.User;
import com.qq.process.ClientProcessI;

public class AddFriendProcess implements ClientProcessI {

	@Override
	public void Process(Socket sk, Request rq, Response rp) {

		if (rp.getResponsecode() == Response.ADDFRIEND_OK) {
			JOptionPane.showMessageDialog(null, "添加成功！");
			
			Request rq1 = new Request();
			User u = new User();
			u.setQq(rp.getQq());
			rq1.setRequestname("Friends");
			rq1.setUser(u);
			//向服务器发送刷新好友列表请求
			try {
				OutputStream os = sk.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(rq1);
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (rp.getResponsecode() == Response.ADDFRIEND_FAIL) {
			JOptionPane.showMessageDialog(null, "账号不存在！");
		} else
			JOptionPane.showMessageDialog(null, "添加失败！");
	}

}
