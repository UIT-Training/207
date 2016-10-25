package server.model;

import java.util.HashMap;

public class ManagerClientThread {
	
	public static HashMap hashMap = new HashMap<String,SerConClientThread>();
	
	public static void addClientThread(String userID,SerConClientThread serConClientThread){
		
		hashMap.put(userID, serConClientThread);
	}
	
	public static SerConClientThread getClientThread(String userID){
		
		return (SerConClientThread)hashMap.get(userID);
		
	}
}
