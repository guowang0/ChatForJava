package com.qq.process.impl;

import java.net.Socket;

import com.qq.Window.WindowChat;
import com.qq.WindowEvent.WindowChatEvent;
import com.qq.bean.Information;
import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.bean.User;
import com.qq.process.ClientProcessI;

public class ChatProcess implements ClientProcessI {

	@Override
	public void Process(Socket sk, Request rq, Response rp) {

		if (!(checkchatwindow(rp.getQq()) == -1)) {
			Information.li
					.get(checkchatwindow(rp.getQq()))
					.getTa1()
					.append(rp.getUser().getNick() + "   " + WindowChatEvent.Time()
							+ "\r\n");
			Information.li
			.get(checkchatwindow(rp.getQq()))
			.getTa1()
			.append( "  " + rp.getMessage() + "\r\n");
		}else{
			User u = rp.getUser();
			Information.li.add(new WindowChat(u));
			Process(sk, rq, rp);
		}
	}

	public static int checkchatwindow(String id) {

		for (int i = 0; i < Information.li.size(); i++) {
			if (Information.li.get(i).getU().getQq().equals(id))
				return i;
		}
		return -1;
	}
}
