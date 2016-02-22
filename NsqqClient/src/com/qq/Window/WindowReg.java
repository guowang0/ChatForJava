 package com.qq.Window;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.qq.WindowEvent.WindowRegEvent;

public class WindowReg extends JFrame{
	
	private JLabel lb1;
	private JLabel lb2;
	private JLabel lb3;
	
	private JTextField tf;
	private JPasswordField pf1;
	private JPasswordField pf2;
	
	private JButton bt1;
	private JButton bt2;
	
	public WindowReg(){
		
		
		lb1 = new JLabel("   昵称 ：");
		lb2 = new JLabel("   密码 ：");
		lb3 = new JLabel("再次输入:");
		
		tf = new JTextField("", 10);
		pf1 = new JPasswordField("", 10);
		pf2 = new JPasswordField("", 10);
		
		bt1 = new JButton("确定");
		bt2 = new JButton("取消");
		
		this.setVisible(true);
		this.setTitle("用户注册");
		this.setSize(240, 280);
		this.setLocation(500, 230);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));
		this.setResizable(false);
		
		this.add(lb1);
		this.add(tf);
		this.add(lb2);
		this.add(pf1);
		this.add(lb3);
		this.add(pf2);
		this.add(bt1);
		this.add(bt2);
		
		bt1.addActionListener(new WindowRegEvent("bt1", this, tf, pf1, pf2));
		bt2.addActionListener(new WindowRegEvent("bt2", this, tf, pf1, pf2));
		
	}

}
