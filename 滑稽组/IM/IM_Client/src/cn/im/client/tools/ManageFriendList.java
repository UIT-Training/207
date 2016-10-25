package cn.im.client.tools;

import java.util.HashMap;
import java.util.Map;

import cn.im.client.view.FriendList;


/**
 * 
 * 管理好友 列表
 * @author linCQ
 *
 */
public class ManageFriendList {

	public static Map<String, FriendList> listPool = new HashMap<>();// 创建一个列表集合，用来管理所有的好友列表

	public static void addFriendList(String userCount, FriendList friendList) {
		listPool.put(userCount, friendList);
	}

	public static FriendList getFriendList(String userCount)// 实现用用户名 获取当前  用户的通信线程
	{
		return listPool.get(userCount);
	}
}
