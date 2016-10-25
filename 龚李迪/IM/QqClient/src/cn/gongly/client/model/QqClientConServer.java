package cn.gongly.client.model;
/**
 * 后台
 * @author MSI
 *
 */

import java.util.*;
import cn.gongly.client.tools.*;
import java.io.*;
import java.net.*;
import cn.gongly.common.*;

public class QqClientConServer {

	private static final String User = null;
	public Socket s;

	// public QqClientConServer() {
	// try {
	// Socket s =new Socket("127.0.0.1",9999);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// 发送第一次请求
	boolean sendLoginInfoToServer(Object o) {

		boolean b = false;

		try {
			System.out.println("socket");
			s = new Socket("127.0.0.1", 9999);//127.0.0.1

			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message) ois.readObject();

			// 这里就是验证用户登录的地方
			if (ms.getMesType().equals("1")) {
				// 就创建一个该qq号和服务器端保持通讯连接得线程
				System.out.println("线程");
				ClientConServerThread ccst = new ClientConServerThread(s);
				// 启动该通讯线程
				ccst.start();
				ManagerClientConServerThread.addClientConServerThread(((User) o).getUserID(), ccst);
				b = true;
			} else {
				// 关闭Scoket
				s.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
}
// private void SendInfoToServer(Object o) {
// try {
// Socket s =new Socket("127.0.0.1",9999);
//
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }
