package com.qq.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import com.qq.Tools.Time;

public class Mainthread implements Runnable{
	
	JTextArea ta;
	
	public Mainthread(JTextArea ta){
		
		this.ta = ta;
	}
	@Override
	public void run() {
		
		//���÷������˿�
		ServerSocket ss = null;
		Socket sk = null;
		try {
			ss = new ServerSocket(8008);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ta.append(Time.ViewTime()+":������������...\r\n");
		
		while(true){
			
			try {
				sk = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ta.append(Time.ViewTime()+":"+sk.getInetAddress()+"�����ӵ�������..\r\n");
			SonThread st = new SonThread(ta, sk);
			Thread T = new Thread(st);
			T.start();
		}
	}
	
	
}
