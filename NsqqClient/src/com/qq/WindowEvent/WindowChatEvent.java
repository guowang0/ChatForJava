package com.qq.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;

import javax.swing.JTextArea;

import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.bean.User;

public class WindowChatEvent implements ActionListener ,KeyListener{

	private String s;
	private JTextArea ta1;
	private JTextArea ta2;
	private User u;

	public WindowChatEvent(User u, String s, JTextArea ta1, JTextArea ta2) {

		this.u = u;
		this.s = s;
		this.ta1 = ta1;
		this.ta2 = ta2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (s.equals("send")) {
			Request rq = new Request();
			rq.setRequestname("Message");
			rq.setQq(Information.getU().getQq());
			rq.setToqq(u.getQq());
			rq.setMessage(ta2.getText());
			rq.setUser(Information.getU());
			
			ta1.append(Information.getU().getNick()+"   "+Time()+"\r\n");
			ta1.append("  "+ta2.getText()+"\r\n");
			ta2.setText(null);
			
			Socket sk = Information.getSk();
			try {
				OutputStream os = sk.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(rq);
				oos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static String Time() {
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int data = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		return year + "/" + month + "/" + data + "   " + hour + ":" + minute
				+ ":" + second;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == 10){
			
			Request rq = new Request();
			rq.setRequestname("Message");
			rq.setQq(Information.getU().getQq());
			rq.setToqq(u.getQq());
			rq.setMessage(ta2.getText());
			rq.setUser(Information.getU());
			
			ta1.append(Information.getU().getNick()+"   "+Time()+"\r\n");
			ta1.append("  "+ta2.getText()+"\r\n");
			
			Socket sk = Information.getSk();
			try {
				OutputStream os = sk.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(rq);
				oos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == 10)
		ta2.setText(null);
	}
}
