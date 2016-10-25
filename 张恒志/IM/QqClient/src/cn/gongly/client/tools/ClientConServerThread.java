package cn.gongly.client.tools;

/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�.
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

	// ���캯��
	public ClientConServerThread(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			// ��ͣ�Ķ�ȡ�ӷ�����������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				// System.out.println("
				// ��ȡ����������������Ϣ"+m.getSender()+"��"+m.getGetter()+"����"+m.getCon());

				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// �Ѵӷ�������õ���Ϣ,��ʾ���������
					QqChat qqChat = ManageQqchat.getQqChat(m.getGetter() + " " + m.getSender());
					qqChat.showMessage(m);
				} else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)) {
					String con = m.getCon();
					String friend[] = con.split(" ");// ����ַ���
					String getter = m.getGetter();
					// �޸���Ӧ�ĺ����б�
					QqFriendList qqFriendList = ManageQqFriendList.getFriendList(getter);

					// �������ߺ���
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
