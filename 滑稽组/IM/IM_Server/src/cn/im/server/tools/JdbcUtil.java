package cn.im.server.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 
 * JDBC 连接数据库 工具类
 * @author linCQ
 *
 */
public class JdbcUtil {
	private JdbcUtil() {
	}// 防止类外构造

	private static String URL = "jdbc:mysql://localhost:3306/im";
	private static String USER = "root";
	private static String PASSWORD = "1071895900";
	
	/**
	 * 加载jdbc 驱动程序
	 */
	static { 	// 不能再静态块里构造静态变量，最好放在块外
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("成功连接mysql服务器。");
		} catch (ClassNotFoundException e) {
			
			System.out.println("连接错误");
			throw new RuntimeException();
		}
	}

	/**
	 * 获取jdbc连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException// 创建连接方法,因为每一个操纵都需要得到一个连接，所以需要返回值
	{
		Connection conn = null;
		conn = DriverManager.getConnection(URL, USER, PASSWORD);

		return conn;
	}

	/**
	 * 关闭资源
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void close( Connection conn,Statement st,ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new JdbcUtil();
	}
}