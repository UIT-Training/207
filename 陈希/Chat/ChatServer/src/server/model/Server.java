package server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import common.Message;
import common.User;

public class Server {
	
	public Server(){
		
		try {
			
			System.out.println("服务器已启动");
			ServerSocket serverSocket = new ServerSocket(6666);
			while(true){

				Socket socket = serverSocket.accept();

				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				User user = (User)objectInputStream.readObject();
				Message message = new Message();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				if(user.getPassword().equals("123456")){
					message.setMessageType("1");
					objectOutputStream.writeObject(message);
					
					//单开线程，让该线程与客户端保持联络
					SerConClientThread serConClientThread = new SerConClientThread(socket);
					ManagerClientThread.addClientThread(user.getUserID(), serConClientThread);
					serConClientThread.start();
					
				}else{
					message.setMessageType("2");
					objectOutputStream.writeObject(message);
					socket.close();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
