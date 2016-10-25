package client.tools;

import java.util.HashMap;

import client.view.Chat;

public class ManagerChat {

	private static HashMap hashMap = new HashMap<String, Chat>();
	
	public static void addChat(String loginIDAndFriendID,Chat chat){
		hashMap.put(loginIDAndFriendID, chat);
	}
	
	public static Chat getChat(String loginIDAndFriendID){
		return (Chat)hashMap.get(loginIDAndFriendID);
	}
}
