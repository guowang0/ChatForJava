package com.qq.WindowEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import javax.swing.JList;

import com.qq.Window.WindowChat;
import com.qq.bean.Information;
import com.qq.bean.User;

public class WindowListEvent implements MouseListener {

	JList<User> l;

	public WindowListEvent(JList<User> l) {

		this.l = l;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			User u = l.getModel().getElementAt(l.locationToIndex(e.getPoint()));
			//将聊天窗口保存在li中
			Information.li.add(new WindowChat(u));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
