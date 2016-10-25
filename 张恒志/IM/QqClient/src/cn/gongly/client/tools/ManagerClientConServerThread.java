package cn.gongly.client.tools;

/**
 * 管理客户端与服务器的通讯的线程类
 * @author MSI
 *
 */
import java.util.*;

public class ManagerClientConServerThread {

	static HashMap hm = new HashMap<String, ClientConServerThread>();

	// 把创建好的线程放入HSHMAP

	public static void addClientConServerThread(String qqId, ClientConServerThread ccst) {
		hm.put(qqId, ccst);
	}

	// 可以通过QQID取得该线程

	public static ClientConServerThread getClientConServerThread(String qqId) {
		return (ClientConServerThread) hm.get(qqId);
	}
}
