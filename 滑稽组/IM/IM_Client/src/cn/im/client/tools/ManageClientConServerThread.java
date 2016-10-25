package cn.im.client.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 管理 客户端连接服务器 的线程
 * @author linCQ
 *
 */
public class ManageClientConServerThread {

	public static Map<String, ClientConServerThread> threadPool = new HashMap<>();// 创建一个线程池, 管理 客户端-->服务器 通信线程

	public static void addClientThread(String userCount, ClientConServerThread thread)// 以用户帐户--线程的方法来 匹配
	{
		threadPool.put(userCount, thread);
	}

	public static ClientConServerThread getClientThread(String userCount)//实现用用户名  获取     当前用户的通信线程
	{
		return threadPool.get(userCount);
	}
}
