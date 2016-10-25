package cn.gongly.server.model;
/**
 * 服务器与某个客户端的通讯线程
 * @author MSI
 *
 */

import java.io.*;
import java.util.*;
import java.net.*;

import cn.gongly.common.Message;
import cn.gongly.common.MessageType;

public class ServerConClientThread extends Thread {

	Socket s;

	public ServerConClientThread(Socket s) {
		// 把服务器和该客户端的连接赋给s
		this.s = s;
	}

	// 通知其他用户

	public void notifyOther(String iam) {
		// 得到所有在线的人
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();

		while (it.hasNext()) {
			Message m = new Message();
			m.setCon(iam);// UserId
			m.setMesType(MessageType.message_ret_onLineFriend);// 要求返回在线好友
			// 取出在线的ID
			String onLineUserId = it.next().toString();// 在线好友
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientThread.getaddClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			// 接收客户端的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();

				// System.out.println(m.getSender()+"给"+m.getGetter()+"说"+m.getCon());
				// 对从客户端取得的消息进行类型判断,然后做相应的处理
				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// 待会转发
					// 取得通讯人的线程
					ServerConClientThread sc = ManageClientThread.getaddClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);

				} else if (m.getMesType().equals(MessageType.message_get_onLineFriend)) {
					// 把服务器的好友给他返回
					String res = ManageClientThread.getAllonLineUserId();
					Message m2 = new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
