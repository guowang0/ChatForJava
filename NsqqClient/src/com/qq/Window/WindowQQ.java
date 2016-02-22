package com.qq.Window;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.qq.WindowEvent.WindowListEvent;
import com.qq.WindowEvent.WindowLoginEvent;
import com.qq.WindowEvent.WindowQQButtonEvent;
import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.bean.User;

public class WindowQQ extends JFrame implements WindowListener{

	private JButton bt;
	private JPanel pl1;
	private JPanel pl2;
	private JScrollPane sp;
	private JLabel lb;
	public static JList<User> l;
	
	public WindowQQ(){
		
		l = new JList<User>();
		pl1 = new JPanel();
		pl2 = new JPanel();
		bt = new JButton("添加好友");
		sp = new JScrollPane(l);
		lb = new JLabel("我的好友");
		
		this.setTitle(Information.getU().getNick());
		this.setVisible(true);
		this.setSize(250, 500);
		this.setLocation(900, 100);
		this.setLayout(new BorderLayout());
		this.add(pl1,"Center");
		this.add(pl2,"South");
		pl2.add(bt);
		pl1.setVisible(true);
		pl1.setLayout(new BorderLayout());
		pl1.add(lb,"North");
		pl1.add(sp,"Center");
		
		bt.addActionListener(new WindowQQButtonEvent("bt"));
		l.addMouseListener(new WindowListEvent(l));
		this.addWindowListener(this);
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		//中断线程
		WindowLoginEvent.T.stop();
		// 向服务器发送退出请求
		Request rq = new Request();
		rq.setUser(Information.getU());
		rq.setRequestname("Exit");
		Socket sk = Information.getSk();	
		try {
			OutputStream  os = sk.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(rq);
			oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	
}
