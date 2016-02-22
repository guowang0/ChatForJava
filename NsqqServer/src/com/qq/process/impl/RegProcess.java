package com.qq.process.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JTextArea;

import org.omg.CORBA.portable.InputStream;

import com.qq.MainTest.Test;
import com.qq.Tools.Time;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.bean.User;
import com.qq.process.ServerProcessI;
/**
 * ��������ʵ�����û���ע��
 * @author Administrator
 *
 */
public class RegProcess implements ServerProcessI{

	@Override
	public void Service(Request rq, Response rp, JTextArea ta, Socket sk) {
	//	ta.append("�ѽ���ע��ϵͳ...\r\n");
		//����qq
		int qq = buildqq();
		//��ע�����Ϣд���ļ�
		String path = System.getProperty("user.dir");
		java.io.InputStream is = null;
		try {
			is = new FileInputStream(path+"/src/com/qq/bean/User.properties");
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
		OutputStream os = null;
		try {
			os = new FileOutputStream(path+"/src/com/qq/bean/User.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pr.setProperty(qq+".id", String.valueOf(qq));
		pr.setProperty(qq+".nick", rq.getUser().getNick());
		pr.setProperty(qq+".password", rq.getUser().getPassword());
		
		try {
			pr.store(os, "hello");
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ta.append(Time.ViewTime()+":IP"+sk.getInetAddress()+"ע��ɹ���idΪ"+qq+"...\r\n");
		User u = new User();
		u.setQq(String.valueOf(qq));
		Response rp1 = new Response();
		rp1.setResponsename("reg");
		rp1.setUser(u);
		
		//���Ͷ˷��ͻ�Ӧ
		
		OutputStream os1 = null;
		ObjectOutputStream oos = null;
		try {
			os1 = sk.getOutputStream();
			oos = new ObjectOutputStream(os1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			try {
				oos.writeObject(rp1);
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		file(qq);
	//	ta.append(Time.ViewTime()+":Ϊid:"+qq+"������ϵͳ�ļ�...\r\n");
		ta.append(Time.ViewTime()+":����:id"+qq+"...������ע����Ϣ...\r\n");
	}
	/**
	 * ����QQ����
	 * @return
	 */
	public int buildqq(){
		
		int qq = (int) (10000*Math.random());
		if(checkqq(qq)){
			
			return qq;
		}
		else{
			buildqq();
		}
		
		return qq;
	}
	
	public boolean checkqq(int qq){
		
		String path = System.getProperty("user.dir");
		Properties pr = new Properties();
		java.io.InputStream is = null;
		try {
			is =new FileInputStream(path+"/src/com/qq/bean/User.properties");
			pr.load(is);
			is.close();
			String key = pr.getProperty(String.valueOf(qq)+".nick");
			if(key != null){
				
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	/**
	 *  Ϊע����û��½����ѹ�ϵ�ļ�
	 * @param qq
	 */
	public void file(int qq){
		
		String path = System.getProperty("user.dir");
		File file = new File(path+"/src/com/qq/bean/Users/"+qq+".properties");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
