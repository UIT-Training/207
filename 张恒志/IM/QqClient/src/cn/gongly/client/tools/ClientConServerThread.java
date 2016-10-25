package cn.gongly.client.tools;

/**
 * 这是客户端和服务器端保持通讯的线程.
 */

import java.io.*;
import java.net.*;

import cn.gongly.client.view.QqChat;
import cn.gongly.client.view.QqFriendList;
import cn.gongly.common.Message;
import cn.gongly.common.MessageType;

public class ClientConServerThread extends Thread {

	private Socket s;

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	// 构造函数
	public ClientConServerThread(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			// 不停的读取从服务器来的消息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				// System.out.println("
				// 读取到服务器发来的消息"+m.getSender()+"给"+m.getGetter()+"内容"+m.getCon());

				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// 把从服务器获得的消息,显示到聊天界面
					QqChat qqChat = ManageQqchat.getQqChat(m.getGetter() + " " + m.getSender());
					qqChat.showMessage(m);
				} else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)) {
					String con = m.getCon();
					String friend[] = con.split(" ");// 拆分字符串
					String getter = m.getGetter();
					// 修改相应的好友列表
					QqFriendList qqFriendList = ManageQqFriendList.getFriendList(getter);

					// 更新在线好友
					if (qqFriendList != null) {
						qqFriendList.updateFriend(m);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
