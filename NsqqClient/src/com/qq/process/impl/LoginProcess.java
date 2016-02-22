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
 * ����ʵ�ֵ�¼�ľ��巽��
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
			
			JOptionPane.showMessageDialog(null, "��¼�ɹ���");
			//���û���Ϣ����ͻ���
			Information.setSk(sk);
			Information.setU(rp.getUser());
			//����QQ����
			new WindowQQ();
			wl.dispose();
		}
		else if(rp.getResponsecode() == Response.LOGIN_NAME){
			
			JOptionPane.showMessageDialog(null, "�˺Ų����ڣ�");
			try {
				sk.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(rp.getResponsecode() == Response.LOGIN_PASSWOARD){
			
			JOptionPane.showMessageDialog(null, "�������");
			try {
				sk.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
