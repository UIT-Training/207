package cn.gongly.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlHelper {
	public static ResultSet rs=null;
	
	public static void SqlCon() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/QQ", "root", "123123");

		// System.out.println(conn);
		Statement stmt = conn.createStatement();

		rs = stmt.executeQuery("select *from User");
		System.out.println("数据库连接成功");
	}
	
	public static ResultSet getRs() {
		return rs;
	}
	public static void setRs(ResultSet rs) {
		SqlHelper.rs = rs;
	}
	
	
}
