package client.tools;

import java.util.HashMap;

public class ManagerClientConServerThread {
	
	private static HashMap hashMap = new HashMap<String,ClientConServerThread>();
	
	//把创建好的线程放入
	public static void addClientConServerThread(String ID,ClientConServerThread clientConServerThread){
		hashMap.put(ID, clientConServerThread);
	}
	
	//通过ID取得该线程
	public static ClientConServerThread getClientConServerThread(String ID){
		return (ClientConServerThread)hashMap.get(ID);
	}
}
