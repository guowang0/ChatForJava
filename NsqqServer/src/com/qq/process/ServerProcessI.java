package com.qq.process;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qq.bean.Request;
import com.qq.bean.Response;
import com.qq.thread.SonThread;
/**
 * ����˴����ͨ�ýӿ�
 * @author Administrator
 *
 */
public interface ServerProcessI {

	void Service(Request rq, Response rp, JTextArea ta, Socket sk);
}
