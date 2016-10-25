package client.tools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import client.view.Chat;
import common.Message;

public class ClientConServerThread extends Thread{

	private Socket socket;
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ClientConServerThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		while(true){
			//不停读取从服务器端发来的消息
			
			try {
				
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				Message message = (Message)objectInputStream.readObject();
				System.out.println("读取到从服务器发来的消息"+message.getSender()+"给"+message.getGetter()+"内容"+message.getCon());
				//把从服务器获得的消息现实到该现实的聊天界面
				Chat chat = ManagerChat.getChat(message.getGetter()+" "+message.getSender());
				chat.showMessage(message);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
}
