package com.qq.Window;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;

import com.qq.WindowEvent.WindowAddFriendEvent;

public class WindowAddFriend extends JFrame implements WindowListener{

	private JLabel lb;
	private JTextField tf;
	private JButton bt;
	
	public WindowAddFriend(){
		
		lb = new JLabel("ID:");
		tf = new JTextField("",10);
		bt = new JButton("确定");
		
		this.setVisible(true);
		this.setTitle("添加好友");
		this.setSize(260, 100);
		this.setLocation(500, 230);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));
		this.setResizable(false);
		
		this.add(lb);
		this.add(tf);
		this.add(bt);
		
		bt.addActionListener(new WindowAddFriendEvent(tf, this));
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
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
