package com.qq.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.omg.CORBA.portable.InputStream;

import com.qq.MainTest.Test;
import com.qq.Window.WindowLogin;
import com.qq.Window.WindowReg;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.bean.User;

public class WindowRegEvent implements ActionListener {

	String s;
	WindowReg wg;
	JTextField tf;
	JPasswordField pf1;
	JPasswordField pf2;

	public WindowRegEvent(String s, WindowReg wg, JTextField tf,
			JPasswordField pf1, JPasswordField pf2) {

		this.s = s;
		this.wg = wg;
		this.tf = tf;
		this.pf1 = pf1;
		this.pf2 = pf2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Socket sk = null;
		
		try {
			sk = new Socket("127.0.0.1",8008);
		} catch (Exception e1) { 
			JOptionPane.showMessageDialog(null, "链接服务器失败");
			e1.printStackTrace();}

		if (s.equals("bt1")) {

			if (pf1.getText().equals(pf2.getText())) {
				
				OutputStream os = null;
				ObjectOutputStream oos = null;
				try {
					os = sk.getOutputStream();
					oos = new ObjectOutputStream(os);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// 向服务器发送注册请求
				Request rq = new Request();
				User u = new User();
				u.setNick(tf.getText());
				u.setPassword(String.valueOf(pf1.getPassword()));
				rq.setUser(u);
				rq.setRequestname("reg");
				
				
					try {
						oos.writeObject(rq);
						oos.flush();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				// 从服务器读取响应
				
					java.io.InputStream is = null;
					ObjectInputStream ois = null;
					try {
						is = sk.getInputStream();
						ois = new ObjectInputStream(is);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Response rp = null;
					try {
						rp = (Response) ois.readObject();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "你注册的id为："+ rp.getUser().getQq());
					wg.dispose();
					new WindowLogin();

			} else {
				JOptionPane.showMessageDialog(null, "您输入的密码不一致！");
			}
		}
		else
			wg.dispose();
		try {
			sk.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
