package com.qq.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.qq.Window.WindowAddFriend;

public class WindowQQButtonEvent implements ActionListener{

	private String s;
	
	public WindowQQButtonEvent(String s){
		
		this.s = s;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(s.equals("bt"))
			new WindowAddFriend();
	}

	
}
