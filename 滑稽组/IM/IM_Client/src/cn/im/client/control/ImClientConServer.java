package cn.im.client.control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.im.client.tools.ClientConServerThread;
import cn.im.client.tools.ManageClientConServerThread;
import cn.im.common.Message;
import cn.im.common.MessageType;
import cn.im.common.User;

public class ImClientConServer {
	private Socket ss;
	public Socket getSs() {
		return ss;
	}

	public boolean sendLoginInfoToServer(User user)//发送登录信息
	{
		try {
			ss= new Socket("127.0.0.1",8888);//连接服务器
			ObjectOutputStream oos= new ObjectOutputStream(ss.getOutputStream());
			oos.writeObject(user);//将用户信息发送给服务器
			
			ObjectInputStream ois= new ObjectInputStream(ss.getInputStream());
			Message ret= (Message)ois.readObject();//服务器回馈信息报
			if(ret.getMesType().equals(MessageType.login_succeed))
			{
				ClientConServerThread cst= new ClientConServerThread(ss);
				ManageClientConServerThread.addClientThread(user.getCount(), cst);
				cst.start();
				return true;
			}
			else return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
