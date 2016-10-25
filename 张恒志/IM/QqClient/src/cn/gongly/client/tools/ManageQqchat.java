package cn.gongly.client.tools;

/**
 * 这是一个管理用户聊天界面的类
 * @author MSI
 *
 */
import java.util.*;
import cn.gongly.client.view.*;

public class ManageQqchat {
	private static HashMap hm = new HashMap<String, QqChat>();

	// 加入
	public static void addQqChat(String loginIdAnFriendId, QqChat qqChat) {
		hm.put(loginIdAnFriendId, qqChat);
	}
	// 取出

	public static QqChat getQqChat(String loginIdAnFriendId) {
		return (QqChat) hm.get(loginIdAnFriendId);
	}
}
