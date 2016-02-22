package com.qq.process.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JTextArea;

import com.qq.Tools.Time;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.process.ServerProcessI;

public class AddFriendProcess implements ServerProcessI {

	@Override
	public void Service(Request rq, Response rp, JTextArea ta, Socket sk) {

		Response rp1 = new Response();
		rp1.setResponsename("AddFriend");
		rp1.setQq(rq.getQq());
		if (LoginProcess.checkid(rq.getToqq())) {
			String path = System.getProperty("user.dir");
			java.io.InputStream is = null;
			try {
				is = new FileInputStream(path + "/src/com/qq/bean/Users/"
						+ rq.getUser().getQq() + ".properties");
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
				os = new FileOutputStream(path + "/src/com/qq/bean/Users/"
						+ rq.getUser().getQq() + ".properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pr.setProperty(rq.getToqq(), "yes");
			try {
				pr.store(os, rq.getQq());
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			java.io.InputStream is1 = null;
			try {
				is1 = new FileInputStream(path + "/src/com/qq/bean/Users/"
						+ rq.getToqq() + ".properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Properties pr1 = new Properties();
			try {
				pr1.load(is1);
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OutputStream os1 = null;
			try {
				os1 = new FileOutputStream(path + "/src/com/qq/bean/Users/"
						+ rq.getToqq() + ".properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(pr1.getProperty(rq.getUser().getQq()) == null)
			pr1.setProperty(rq.getUser().getQq(), "no");
			try {
				pr1.store(os1, rq.getToqq());
				os1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ta.append(Time.ViewTime()+":id:"+rq.getQq() + "成功添加好友id:" + rq.getToqq()+"\r\n");
			rp1.setResponsecode(Response.ADDFRIEND_OK);
		} else {
			rp1.setResponsecode(Response.ADDFRIEND_FAIL);
			ta.append(Time.ViewTime()+":id:"+rq.getQq() + "添加好友id:" + rq.getToqq() + "失败！\r\n");
			ta.append(Time.ViewTime()+":原因id:" + rq.getToqq() + "不存在...\r\n");
		}
		// 向客户端发送回应
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
	}

}
