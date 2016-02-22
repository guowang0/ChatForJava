package com.qq.process.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.swing.JTextArea;

import com.qq.Tools.Time;
import com.qq.Window.ServerWindowEvent;
import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.bean.User;
import com.qq.process.ServerProcessI;

/**
 * 这里实现向客户端发送好友信息
 * 
 * @author Administrator
 * 
 */
public class FriendsProcess implements ServerProcessI {

	@Override
	public void Service(Request rq, Response rp, JTextArea ta, Socket sk) {
		System.out.println(ServerWindowEvent.li.get(0).getU().getNick());
		ta.append(Time.ViewTime()+":id:" + rq.getUser().getQq()
				+ "正在获取好友信息...\r\n");

		String path = System.getProperty("user.dir");
		java.io.InputStream is = null;
		java.io.InputStream isu = null;
		try {
			is = new FileInputStream(path + "/src/com/qq/bean/Users/"
					+ rq.getUser().getQq() + ".properties");
			isu = new FileInputStream(path + "/src/com/qq/bean/User.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties pr = new Properties();
		Properties pru = new Properties();
		try {
			pr.load(is);
			pru.load(isu);
			is.close();
			isu.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 从文件中读取好友信息并存入List中
		List<User> li = new ArrayList<User>();
		Set<Object> s = pr.keySet();
		for (Object qq : s) {
			if (LoginProcess.checkid((String) qq)) {
				User u = new User();
				if(idstatus((String) qq))
					u.setStatus(1);
				else
					u.setStatus(0);
				u.setQq((String) qq);
				u.setNick(pru.getProperty((String) qq + ".nick"));
				li.add(u);
			}
		}
		liremove(li, pr);
		Response rp1 = new Response();
		rp1.setli(li);
		rp1.setResponsename("Friends");

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
		ta.append(Time.ViewTime()+":已向id:" + rq.getUser().getQq() + "返回了好友信息...\r\n");
	}

	// 从List中去掉单向好友（你是别人的好友但并未加此人）
	public static void liremove(List<User> li, Properties pr) { 

		for (int i = 0; i < li.size(); i++) {
			User u = li.get(i);
			if (pr.getProperty(u.getQq()).equals("no"))
				li.remove(i);
		}
	}
	/**
	 * 判断当前的id是否在线
	 * @param qq 
	 * @return 如果在返回true 否侧false
	 */
	public static boolean idstatus(String qq){
		
		for (int i = 0; i < ServerWindowEvent.li.size(); i++) { 
			if(ServerWindowEvent.li.get(i).getU().getQq().equals(qq))
				return true;
		}
		return false;
	}
}
