package com.qq.Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JTextArea;

import org.omg.CORBA.portable.InputStream;

import com.qq.Window.WindowLogin;
import com.qq.bean.Request;
import com.qq.bean.Response;

import com.qq.process.ClientProcessI;
import com.qq.process.impl.AddFriendProcess;
import com.qq.process.impl.ChatProcess;
import com.qq.process.impl.FriendsrefreshProcess;
import com.qq.process.impl.GetFriendsProcess;
import com.qq.process.impl.LoginProcess;

public class MainThread implements Runnable {

	Socket sk;
	WindowLogin wl;

	public MainThread(Socket sk,WindowLogin wl) {

		this.sk = sk;
		this.wl = wl;
	}

	@Override
	public void run() {
		
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
			Response rp = null;
			try {
				rp = (Response) ois.readObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (rp.getResponsename().equals("Friends")) {
				ClientProcessI cpi = new GetFriendsProcess();
				cpi.Process(sk, null, rp);				
			}
			else if(rp.getResponsename().equals("AddFriend")){
				ClientProcessI cpi = new AddFriendProcess();
				cpi.Process(sk, null, rp);	
			}
			else if(rp.getResponsename().equals("Message")){
				ClientProcessI cpi = new ChatProcess();
				cpi.Process(sk, null, rp);	
			}
			else if(rp.getResponsename().equals("Login")){
				ClientProcessI cpi = new LoginProcess(wl);
				cpi.Process(sk, null, rp);	
			}
			else if(rp.getResponsename().equals("Friendsrefresh")){
				ClientProcessI cpi = new FriendsrefreshProcess();
				cpi.Process(sk, null, rp);	
			}
		}
	}
}
