package com.qq.process;

import java.net.Socket;

import com.qq.bean.Request;
import com.qq.bean.Response;
/**
 * 客户端处理的通用接口
 * @author Administrator
 *
 */
public interface ClientProcessI {

		void Process(Socket sk, Request rq ,Response rp);
}
