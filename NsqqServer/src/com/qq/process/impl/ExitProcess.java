package com.qq.process.impl;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

import com.qq.Tools.Time;
import com.qq.Window.ServerWindowEvent;
import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.process.ServerProcessI;

public class ExitProcess implements ServerProcessI{

	@Override
	public void Service(Request rq, Response rp, JTextArea ta, Socket sk) {
		// TODO Auto-generated method stub

		for (int i = 0; i < ServerWindowEvent.li.size(); i++) {
			if(ServerWindowEvent.li.get(i).getU().getQq().equals(rq.getUser().getQq())){
			 ServerWindowEvent.li.remove(i);
			 ta.append(Time.ViewTime()+":id:"+rq.getUser().getQq()+"�ѳɹ�����...\r\n");
			 try {
				sk.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			}
		}
		//�ɹ����ߺ����ǰ���ߵĺ��ѷ���������Ϣ
		LoginProcess.friends(rq.getUser().getQq());
	}

}
