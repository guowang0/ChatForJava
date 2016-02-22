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
			JOptionPane.showMessageDialog(null, "��ӳɹ���");
			
			Request rq1 = new Request();
			User u = new User();
			u.setQq(rp.getQq());
			rq1.setRequestname("Friends");
			rq1.setUser(u);
			//�����������ˢ�º����б�����
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
			JOptionPane.showMessageDialog(null, "�˺Ų����ڣ�");
		} else
			JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
	}

}
