package cn.im.server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

import cn.im.common.Message;
import cn.im.common.MessageType;

/**
 * 服务器 连接 客户端
 * @author linCQ
 *
 */
public class SerConClientThread extends Thread{
	private Socket socket;//服务器接收到的socket
	public SerConClientThread(Socket socket)
	{
		this.socket= socket;
	}
	 
	public Socket getSocket() {
		return socket;
	}

	public void notifyOthers(String myCount)//通知其他在线的人进行刷新
	{
		Map threadPool= ManageClientThread.threadPool;
		Iterator it= threadPool.keySet().iterator();
		Message message= new Message();
		message.setContent(myCount);//也就是告诉其他线程，去更新我这个id吧
		message.setMesType(MessageType.re_onLineFriend);
		while(it.hasNext())
		{
			String onLineFriend= it.next().toString();
			try {
				ObjectOutputStream oos= new ObjectOutputStream(ManageClientThread.getClientThread(onLineFriend).getSocket().getOutputStream());
				message.setReceiver(onLineFriend);
				oos.writeObject(message);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run()
	{
		while(true)
		{
			try {//不断的执行
				ObjectInputStream ois= new ObjectInputStream(socket.getInputStream());
				Message message=(Message)ois.readObject();
				
				if(message.getMesType().equals(MessageType.common_mess))//普通消息的处理
				{//转发消息
					Socket ss= ManageClientThread.getClientThread(message.getReceiver()).getSocket();
					//ss是从线程池中  找到的收件人与服务器  连接的socket对象
					ObjectOutputStream oos= new ObjectOutputStream(ss.getOutputStream());
					oos.writeObject(message);
				}else if(message.getMesType().equals(MessageType.get_onLineFriend))
				{
					String friends= ManageClientThread.getAllOnLineFriend();
					Message mess= new Message();
					mess.setMesType(MessageType.re_onLineFriend);
					mess.setContent(friends);//从服务器线程管理器  获取到的在线好友列表
					mess.setReceiver(message.getSender());//将原先的发件人设置为反馈报的收件人
					ObjectOutputStream oos= new ObjectOutputStream(ManageClientThread.getClientThread(message.getSender()).getSocket().getOutputStream());
					oos.writeObject(mess);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}finally{
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
