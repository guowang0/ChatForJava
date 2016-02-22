package com.qq.process.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

import com.qq.Tools.Time;
import com.qq.Window.ServerWindowEvent;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.bean.User;
import com.qq.process.ServerProcessI;

public class ChatProcess implements ServerProcessI {

	@Override
	public void Service(Request rq, Response rp, JTextArea ta, Socket sk) {

		Socket sk1 = new Socket();
		boolean b = false;
		for (int i = 0; i < ServerWindowEvent.li.size(); i++) {

			if (rq.getToqq().equals(ServerWindowEvent.li.get(i).getU().getQq())) {

				sk1 = ServerWindowEvent.li.get(i).getSk();
				Response rp1 = new Response();
				rp1.setMessage(rq.getMessage());
				rp1.setUser(rq.getUser());
				rp1.setQq(rq.getQq());
				rp1.setResponsename("Message");
				try {
					OutputStream os = sk1.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(rp1);
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ta.append(Time.ViewTime()+":id:"+rq.getQq()+"向id:"+rq.getToqq()+"发送了一条消息\r\n");
				b = true;
			}
		}
		if (!b) {
			Response rp1 = new Response();
			User u = new User();
			u.setNick("系统消息");
			u.setQq(rq.getToqq());
			rp1.setUser(u);
			rp1.setQq(rq.getToqq());
			rp1.setMessage("当前用户不在线");
			rp1.setResponsename("Message");
			try {
				OutputStream os = sk.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(rp1);
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ta.append(Time.ViewTime()+":id:"+rq.getToqq()+"不在线,id:"+rq.getQq()+"发送消息失败\r\n");
		}
	}

}
