package com.qq.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import com.qq.Thread.MainThread;
import com.qq.Window.WindowLogin;
import com.qq.Window.WindowQQ;
import com.qq.Window.WindowReg;
import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.bean.User;
import com.qq.process.ClientProcessI;
import com.qq.process.impl.LoginProcess;

public class WindowLoginEvent implements ActionListener{
	
	String s;
	WindowLogin wl;
	JTextField tf;
	JPasswordField pf;
	public static Thread T;
	
	public WindowLoginEvent(String s,WindowLogin wl,JPasswordField pf,JTextField tf){
		
		this.s = s;
		this.wl = wl; 
		this.pf = pf;
		this.tf = tf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(s.equals("bt1")){
			Socket sk = null;
			try {
				sk = new Socket("127.0.0.1",8008);
			} catch (Exception e1) { 
				JOptionPane.showMessageDialog(null, "链接服务器失败");
				e1.printStackTrace();
			}
			
			Request rq = new Request();
			User u = new User();
			u.setQq(tf.getText());
			u.setPassword(pf.getText());
			rq.setUser(u);
			rq.setRequestname("login");
			
			//向服务器发送Request
			try {
				OutputStream os = sk.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(rq);
				oos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			MainThread mt = new MainThread(sk,wl);
			T = new Thread(mt);
			T.start();
			
				
			wl.dispose();
				
			
		}
		else{
			new WindowReg();
			wl.dispose();
		}
		
	}

}
