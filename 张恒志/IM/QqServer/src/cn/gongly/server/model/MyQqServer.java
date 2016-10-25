package cn.gongly.server.model;

/**
 * QQ服务器,它在监听,等待
 * @author MSI
 *
 */
import java.net.*;
import java.io.*;
import java.util.*;

import cn.gongly.common.Message;
import cn.gongly.common.User;
import java.sql.*;

public class MyQqServer {

	public MyQqServer() {

		try {
			// 连接数据库
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/QQ", "root", "123123");

			// System.out.println(conn);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select *from User");
			// 监听
			System.out.println("我是服务器在9999监听");
			ServerSocket ss = new ServerSocket(9999);
			// 阻塞,等待连接
			while (true) {
				Socket s = ss.accept();

				// 接收客户端的信息

				// BufferedReader br = new BufferedReader(new
				// InputStreamReader(s.getInputStream()));
				// String info =br.readLine();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User) ois.readObject();

				Message m = new Message();

				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				while (rs.next()) {
					if (u.getUserID().equals(rs.getString("id"))) {
						if (u.getPasswd().equals(rs.getString("passwd"))) {
							// 成功
							System.out.println("服务器接收到用户:" + u.getUserID() + " 密码:" + u.getPasswd());
							m.setMesType("1");
							oos.writeObject(m);

							// 这里单开一个线程,与客户端保持通讯
							ServerConClientThread scct = new ServerConClientThread(s);
							ManageClientThread.addClientThread(u.getUserID(), scct);
							scct.start();

							// 通知其他在线用户
							scct.notifyOther(u.getUserID());

						} else {
							m.setMesType("2");
							oos.writeObject(m);
							// guanbi
							s.close();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

		}
	}
}
