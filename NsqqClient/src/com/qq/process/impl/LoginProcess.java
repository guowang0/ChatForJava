package com.qq.process.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import org.omg.CORBA.portable.InputStream;

import com.qq.Thread.MainThread;
import com.qq.Window.WindowLogin;
import com.qq.Window.WindowQQ;
import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.process.ClientProcessI;
/**
 * 这里实现登录的具体方法
 * @author Administrator
 *
 */
public class LoginProcess implements ClientProcessI{
	
	WindowLogin wl;
	public LoginProcess(WindowLogin wl){
		
		this.wl = wl;
	}
	@Override
	public void Process(Socket sk, Request rq, Response rp) {
		


		if(rp.getResponsecode() == Response.LOGIN_OK){
			
			JOptionPane.showMessageDialog(null, "登录成功！");
			//将用户信息存入客户端
			Information.setSk(sk);
			Information.setU(rp.getUser());
			//启动QQ窗口
			new WindowQQ();
			wl.dispose();
		}
		else if(rp.getResponsecode() == Response.LOGIN_NAME){
			
			JOptionPane.showMessageDialog(null, "账号不存在！");
			try {
				sk.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(rp.getResponsecode() == Response.LOGIN_PASSWOARD){
			
			JOptionPane.showMessageDialog(null, "密码错误！");
			try {
				sk.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
