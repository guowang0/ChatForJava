package com.qq.Window;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

import javax.swing.*;

import com.qq.WindowEvent.WindowLoginEvent;

public class WindowLogin extends JFrame {

	JButton bt1 = new JButton("µÇÂ¼");
	JButton bt2 = new JButton("×¢²á");

	JLabel lb1 = new JLabel("ÕËºÅ");
	JLabel lb2 = new JLabel("ÃÜÂë");

	JTextField tf = new JTextField("", 12);
	JPasswordField pf = new JPasswordField("", 12);

	public WindowLogin() {
		
		
		this.setTitle("QQ¿Í»§¶Ë");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(500, 230);
		this.setSize(240, 160);

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));

		bt1.setBackground(Color.white);
		bt2.setBackground(Color.white);
		this.add(lb1);
		this.add(tf);
		this.add(lb2);
		this.add(pf);
		this.add(bt1);
		this.add(bt2);
		this.setResizable(false);
		bt1.addActionListener(new WindowLoginEvent("bt1",this,pf,tf));
		bt2.addActionListener(new WindowLoginEvent("bt2",this,pf,tf));
	}



}