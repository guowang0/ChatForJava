package com.qq.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import com.qq.Tools.Time;
import com.qq.bean.Information;
import com.qq.thread.Mainthread;

public class ServerWindowEvent implements ActionListener {

	JTextArea ta;
	String s;
	// ������������û���Ϣ
	public static List<Information> li;

	public ServerWindowEvent(JTextArea ta, String s) {

		this.ta = ta;
		this.s = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (s.equals("bt1")) {
			li = new ArrayList<Information>();
			Mainthread mt = new Mainthread(ta);
			Thread T1 = new Thread(mt);
			T1.start();
		} else if(s.equals("bt2")){
			if (li == null)
				ta.append(Time.ViewTime()+":"+"��ǰ��δ����������...\r\n");
			else {
				if (li.size() == 0)
					ta.append(Time.ViewTime()+":"+"��ǰ���û�����...\r\n");
				for (int i = 0; i < li.size(); i++) {
					ta.append(Time.ViewTime()+":"+"id:" + li.get(i).getU().getQq() + "����...\r\n");
				}
			}
		}
		else if(s.equals("bt3")){
			ta.setText("");
		}
	}
}
