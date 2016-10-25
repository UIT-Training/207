
package server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.Message;

public class SerConClientThread extends Thread{
	
	Socket socket;
	
	public SerConClientThread (Socket socket) {
		this.socket = socket;
		
	}
	public void run(){
		
		while (true) {
			//接受发送客户端的信息
			try {
				
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				Message message = (Message)objectInputStream.readObject();
				
				System.out.println(message.getSender()+"给"+message.getGetter()+"说"+message.getCon());
			
				//取得接受人的通讯线程
				SerConClientThread serConClientThread = ManagerClientThread.getClientThread(message.getGetter());
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(serConClientThread.socket.getOutputStream());
				objectOutputStream.writeObject(message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
