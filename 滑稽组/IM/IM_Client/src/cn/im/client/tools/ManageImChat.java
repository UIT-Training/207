package cn.im.client.tools;

import java.util.HashMap;
import java.util.Map;

import cn.im.client.view.ImChat;


/**
 * 管理聊天
 * 
 * @author linCQ
 *
 */
public class ManageImChat {
	
	public static Map<String, ImChat> chatPool = new HashMap<>();// 创建一个聊天窗口池

	public static void addImChat(String senderAndReceiver, ImChat chat)// 用户帐户--线程  匹配
	{
		chatPool.put(senderAndReceiver, chat);
	}

	public static ImChat getImChat(String senderAndReceiver)// 实现用用户名 获取   当前用户的通信线程
	{
		return chatPool.get(senderAndReceiver);
	}
}
