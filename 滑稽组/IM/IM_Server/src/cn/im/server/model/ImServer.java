package cn.im.server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import cn.im.common.Message;
import cn.im.common.MessageType;
import cn.im.common.User;
import cn.im.server.db.UserDaoImpl;

public class ImServer {

	public static ServerSocket server;
	public static Socket ss;
	public ImServer() {
		
		try {
			server= new ServerSocket(8888);//服务器在8888端口进行监听
			System.out.println("服务器在8888端口监听...");
			
			while(true){
				ss= server.accept();//接收客户端发来的socket，成为服务端和客户端之间通信的桥梁（套接字）
				ObjectInputStream ois= new ObjectInputStream(ss.getInputStream());
				User user= (User)ois.readObject();//读取一个对象
				
				ObjectOutputStream oos= new ObjectOutputStream(ss.getOutputStream());
				Message ms= new Message();
				
				if(user.getType().equals("register")){//user默认的类型是login
					System.out.println("服务器端接收到用户"+user.toString());
					if(register(user))
					{
						ms.setMesType(MessageType.regist_succeed);
					}
					else ms.setMesType(MessageType.regist_fail);
					oos.writeObject(ms);//回发一个数据包，而非用户
				}
				else {
					if(checkFromDb(user))
					{
						ms.setMesType(MessageType.login_succeed);
						oos.writeObject(ms);//登录成功，为什么要关闭当前客户端与服务器的连接呢
						SerConClientThread sct= new SerConClientThread(ss);//服务器与客户端信息交流线程
						ManageClientThread.addClientThread(user.getCount(), sct);//这里的线程所有者应该是用户，而非短信发件人
						sct.start();
						
						sct.notifyOthers(user.getCount());//通知其他在线用户进行刷新本用户
					}
					else {
						ms.setMesType(MessageType.login_fail);//可以不要这句话，但是这样的话，就必须要给type赋初值
						oos.writeObject(ms);
						ss.close();//如果验证失败，就需要关闭连接，重新进行连接监听
					}//这里需要到数据库中去验证用户信息
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	public boolean checkFromDb(User user)//校验用户帐号信息数据
	{
		UserDaoImpl udi= new UserDaoImpl();
		try {
			User ruser= udi.queryByCount(user.getCount());
			if(ruser!= null && user.getPsd().equals(ruser.getPsd()))
			{
				return true;
			}
			else return false ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean register(User user)
	{
		UserDaoImpl udi= new UserDaoImpl();
		try {
			udi.add(user);//添加用户
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public static List<User> state()
	{
		UserDaoImpl udi= new UserDaoImpl();
		try {
			return udi.queryAll();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
