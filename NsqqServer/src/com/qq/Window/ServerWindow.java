package com.qq.Window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerWindow extends JFrame {

	private JScrollPane sp;
	private JTextArea ta;
	private JButton bt1;
	private JButton bt2;
	private JButton bt3;
	private JPanel p;
	
	public ServerWindow(){

		ta = new JTextArea();
		sp = new JScrollPane(ta);
		bt1 = new JButton("启动服务");
		bt2 = new JButton("在线用户");
		bt3 = new JButton("清空日志");
		p = new JPanel();
		
		this.setTitle("QQ服务端");
		this.setSize(300, 500);
		this.setLocation(300, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(sp,"Center");
		this.add(p,"South");
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		p.add(bt1);
		p.add(bt2);
		p.add(bt3);
		ta.setEditable(false);
		bt1.addActionListener(new ServerWindowEvent(ta,"bt1"));
		bt2.addActionListener(new ServerWindowEvent(ta,"bt2"));
		bt3.addActionListener(new ServerWindowEvent(ta,"bt3"));
	}

}

