package cn.gongly.server.model;

import java.util.HashMap;

import java.util.*;

public class ManageClientThread {

	public static HashMap hm = new HashMap<String, ServerConClientThread>();

	// 向H中添加客户端通讯线程
	public static void addClientThread(String uid, ServerConClientThread ct) {
		hm.put(uid, ct);
	}

	public static ServerConClientThread getaddClientThread(String uid) {
		return (ServerConClientThread) hm.get(uid);// 获取当前Id的线程
	}

	// 返回在线的好友的情况
	public static String getAllonLineUserId() {
		// 使用迭代器
		Iterator it = hm.keySet().iterator();

		String res = "";// 空字符
		while (it.hasNext()) {
			res += it.next().toString() + " ";// 以空字符分割在线好友
		}
		return res;
	}
}
