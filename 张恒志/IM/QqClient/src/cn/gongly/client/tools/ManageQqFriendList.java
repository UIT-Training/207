package cn.gongly.client.tools;
/**
 * ��������
 * @author MSI
 *
 */

import java.util.*;

import cn.gongly.client.view.QqFriendList;

import java.io.*;

public class ManageQqFriendList {

	private static HashMap hm = new HashMap<String, QqFriendList>();

	public static void addQqFriendList(String qqid, QqFriendList qqFriendList) {
		hm.put(qqid, qqFriendList);

	}

	public static QqFriendList getFriendList(String qqId) {
		return (QqFriendList) hm.get(qqId);
	}
}
