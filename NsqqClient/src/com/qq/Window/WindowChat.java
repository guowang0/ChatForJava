package com.qq.Window;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.qq.WindowEvent.WindowChatEvent;
import com.qq.bean.Information;
import com.qq.bean.User;

public class WindowChat extends JFrame implements WindowListener{

	private JButton bt;
	private JTextArea ta1;
	private JTextArea ta2;
	private JPanel pl1;
	private JPanel pl2;
	private JScrollPane sp1;
	private JScrollPane sp2;

	private JLabel lb;
	private User u;

	public WindowChat(User u){
		
		this.u = u;
		ta1 = new JTextArea();
		ta2 = new JTextArea();
		pl1 = new JPanel();
		pl2 = new JPanel();
		sp1 = new JScrollPane(ta1);
		sp2 = new JScrollPane(ta2);
		lb = new JLabel("请输入你要发送的消息：");
		bt = new JButton("发送");
		
		this.setTitle("与"+u.getNick()+"聊天");
		this.setVisible(true);
		this.setSize(380, 350);
		this.setLocation(400, 180);
		this.setLayout(new BorderLayout());
		pl1.setLayout(new BorderLayout());
		pl2.setLayout(new BorderLayout());
		this.add(pl1,"Center");
		this.add(pl2, "South");
		pl1.add(sp1,"Center");
		pl1.add(lb,"South");
		pl2.add(sp2,"Center");
		pl2.add(bt,"East");
		ta1.setEditable(false);
		ta1.setLineWrap(true);
		ta2.setLineWrap(true);
		bt.addActionListener(new WindowChatEvent(u, "send", ta1, ta2));
		this.addWindowListener(this);
		ta2.addKeyListener(new WindowChatEvent(u, "send", ta1, ta2));
	}

	public User getU() {
		return u;
	}
	
	public JTextArea getTa1() {
		return ta1;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < Information.li.size(); i++) {
			if(this.getU().equals(Information.li.get(i).getU()))
				Information.li.remove(i);
		}
		this.dispose();
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