package com.qq.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JTextArea;

import org.omg.CORBA.portable.InputStream;

import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.process.ServerProcessI;
import com.qq.process.impl.AddFriendProcess;
import com.qq.process.impl.ChatProcess;
import com.qq.process.impl.ExitProcess;
import com.qq.process.impl.FriendsProcess;
import com.qq.process.impl.LoginProcess;
import com.qq.process.impl.RegProcess;

public class SonThread implements Runnable {

	JTextArea ta;
	Socket sk;

	public SonThread(JTextArea ta, Socket sk) {

		this.ta = ta;
		this.sk = sk;
	}

	@Override
	public void run() {

		try {
			while (true) {
				
				// 拿到对象的输入流
				java.io.InputStream is = null;
				ObjectInputStream ois = null;
				try {
					is = sk.getInputStream();
					ois = new ObjectInputStream(is);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Request rq = null;

				rq = (Request) ois.readObject();

				if (rq.getRequestname().equals("reg")) {

					ServerProcessI spi = new RegProcess();
					spi.Service(rq, null, ta, sk);
				} 
				else if (rq.getRequestname().equals("login")) {

					ServerProcessI spi = new LoginProcess();
					spi.Service(rq, null, ta, sk);
				}
				else if (rq.getRequestname().equals("AddFriend")) {

					ServerProcessI spi = new AddFriendProcess();
					spi.Service(rq, null, ta, sk);
				}
				else if (rq.getRequestname().equals("Friends")) {

					ServerProcessI spi = new FriendsProcess();
					spi.Service(rq, null, ta, sk);
				}
				else if (rq.getRequestname().equals("Message")) {

					ServerProcessI spi = new ChatProcess();
					spi.Service(rq, null, ta, sk);
				}
				else if (rq.getRequestname().equals("Exit")) {

					ServerProcessI spi = new ExitProcess();
					spi.Service(rq, null, ta, sk);
					break;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
