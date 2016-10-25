package client.model;

import com.sun.xml.internal.ws.client.SenderException;

import common.User;

public class ClientUser {
	
	public boolean checkUser(User user){
		
		return new ClientConServer().sendLoginInfoToServer(user);
		
	}
	
}
