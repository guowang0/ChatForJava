package com.qq.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextField;

import com.qq.Window.WindowAddFriend;
import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.process.ClientProcessI;
import com.qq.process.impl.AddFriendProcess;

public class WindowAddFriendEvent implements ActionListener{

	JTextField tf;
	WindowAddFriend waf;
	
	public WindowAddFriendEvent(JTextField tf,WindowAddFriend waf){
		
		this.tf = tf;
		this.waf = waf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Request rq = new Request();
		rq.setRequestname("AddFriend");
		rq.setUser(Information.getU());
		rq.setQq(Information.getU().getQq());
		rq.setToqq(tf.getText());
		
		// 向服务器发送Request
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
		waf.dispose();
	}

}
