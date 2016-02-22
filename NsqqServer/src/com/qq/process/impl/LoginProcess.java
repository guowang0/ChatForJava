package com.qq.process.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.swing.JTextArea;

import org.omg.CORBA.portable.InputStream;

import com.qq.Tools.Time;
import com.qq.Window.ServerWindowEvent;
import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.bean.User;
import com.qq.process.ServerProcessI;

public class LoginProcess implements ServerProcessI {
	

	@Override
	public void Service(Request rq, Response rp, JTextArea ta, Socket sk) {

		ta.append(Time.ViewTime()+":"+sk.getInetAddress()+"id:" + rq.getUser().getQq() + "���ڳ��Ե�¼...\r\n");

		String id = rq.getUser().getQq();
		String password = rq.getUser().getPassword();
		Response rp1 = new Response();
		rp1.setResponsename("Login");
		if (checkid(id)) {
			if (checkpass(id, password)) {
				rp1.setResponsecode(Response.LOGIN_OK);
				ta.append(Time.ViewTime()+":"+"id:" + id + "�ѳɹ���¼\r\n");
				ta.append(Time.ViewTime()+":"+"id"+id+"��¼IP:"+sk.getInetAddress()+"\r\n");
				Information ifm = new Information();
				//��ȡ��½�ɹ����û���Ϣ
				Properties pr = new Properties();
				String path = System.getProperty("user.dir");
				java.io.InputStream is = null;
				try {
					is = new FileInputStream(path + "/src/com/qq/bean/User.properties");
					pr.load(is);
					is.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				User u = new User();
				u.setNick(pr.getProperty(id+".nick"));
				u.setQq(id);
				u.setPassword(pr.getProperty(id+".password"));
				rp1.setUser(u);
				ifm.setU(u);
				ifm.setSk(sk);
				ServerWindowEvent.li.add(ifm);
	//			ta.append("�ѽ�"+id+"������Ϣ�����������\r\n");
			}
			else {
				rp1.setResponsecode(Response.LOGIN_PASSWOARD);
				ta.append(Time.ViewTime()+":id:" + id + "��֤����ʧ��\r\n");
			}
		}
		else{
			rp1.setResponsecode(Response.LOGIN_NAME);
			ta.append(Time.ViewTime()+":id:" + id + "�˺Ų�����\r\n");
		}
		
		OutputStream os1 = null;
		try {
			os1 = sk.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os1);
			oos.writeObject(rp1);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rp1.getUser());
		System.out.println(rp1.getResponsecode());
		ta.append(Time.ViewTime()+":����"+sk.getInetAddress()+"...�����˵�¼��Ϣ...\r\n");
		
		if(rp1.getResponsecode() == Response.LOGIN_OK){
			friends(id);
			ServerProcessI spi = new FriendsProcess();
			spi.Service(rq, null, ta, sk);
		}		
	}
	public static boolean checkpass(String id, String password) {

		Properties pr = new Properties();
		String path = System.getProperty("user.dir");
		java.io.InputStream is = null;
		try {
			is = new FileInputStream(path + "/src/com/qq/bean/User.properties");
			pr.load(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��֤������ȷ�����
		if (pr.getProperty(id + ".password").endsWith(password)) {

			return true;
		}
		return false;

	}

	public static boolean checkid(String id) {

		Properties pr = new Properties();
		String path = System.getProperty("user.dir");
		java.io.InputStream is = null;
		try {
			is = new FileInputStream(path + "/src/com/qq/bean/User.properties");
			pr.load(is);
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (pr.getProperty(id + ".id") != null) {

			return true;
		}
		return false;

	}
	// ��¼�ɹ������ǰ���ߵĺ��ѷ��͵�¼��Ϣ
	public static void friends(String id){
		
		String path = System.getProperty("user.dir");
		java.io.InputStream is = null;
		try {
			is = new FileInputStream(path + "/src/com/qq/bean/Users/"
					+ id + ".properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties pr = new Properties();
		try {
			pr.load(is);
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Object> s = pr.keySet();
		for (Object qq : s) {
			for (int i = 0; i < ServerWindowEvent.li.size(); i++){
				if(ServerWindowEvent.li.get(i).getU().getQq().equals(qq)){
					Response rp = new Response();
					rp.setResponsename("Friendsrefresh");
					rp.setQq(id);
					Socket sk = ServerWindowEvent.li.get(i).getSk();
					try {
						OutputStream os = sk.getOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(os);
						oos.writeObject(rp);
						oos.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}			
		}
	}
}
