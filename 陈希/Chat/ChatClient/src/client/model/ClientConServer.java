package client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import client.tools.ClientConServerThread;
import client.tools.ManagerClientConServerThread;
import common.*;


import common.User;

public class ClientConServer {
	
	public Socket socket;
	
	public boolean sendLoginInfoToServer(Object object){
		
		boolean b = false;
		try {
			
			socket = new Socket("127.0.0.1", 6666);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(object);
			
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			
			Message message = (Message)objectInputStream.readObject();
			
			if (message.getMessageType().equals("1")) {
				//创建一个该客户端和服务器保持通讯连接的线程
				ClientConServerThread clientConServerThread = new ClientConServerThread(socket);
				//启动该通讯线程
				clientConServerThread.start();
				ManagerClientConServerThread.addClientConServerThread(((User)object).getUserID(), clientConServerThread);
				b = true;
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return b;
		
	}
	
	
	
	public void sendInfoToServer(Object object){
		try {
			
			Socket socket = new Socket("127.0.0.1", 6666);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
