package cn.im.server.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 管理 客户端与服务器 通信线程
 * @author linCQ
 *
 */
public class ManageClientThread {
	public static Map<String,SerConClientThread> threadPool= new HashMap<>();//创建一个线程池
	
	public static void addClientThread(String userCount, SerConClientThread thread)//用户帐户--线程  匹配
	{
		threadPool.put(userCount, thread);
	}
	
	public static SerConClientThread getClientThread(String userCount)//实现用用户名  获取     当前用户的通信线程
	{
		return threadPool.get(userCount);
	}
	
	public static String getAllOnLineFriend()
	{
		Iterator it= threadPool.keySet().iterator();//将hashmap中的key集合   弄出来   然后生成它的迭代器
		String ret= "";
		while(it.hasNext())
		{
			ret+= it.next().toString()+" ";//以空格分隔用户
		}
		return ret;
	}
}

