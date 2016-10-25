package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
//	private String url = "jdbc:mysql://localhost:3306/jdbc_test";
//	private String user = "root";
//	private String password="1071895900";
	
	private static String url=null;
	private static String user=null;
	private static String password=null;
	private static String driverClass=null;
	
	
	/**
	 * 静态代码块中(只加载一次)
	 */
	static{
		try {
			
			Properties props=new Properties();
			
			/**
			 *  . 代表java命令运行的目录
			 *  在java项目下，. java命令的运行目录从项目的根目录开始
			 *  在web项目下，  . java命令的而运行目录从tomcat/bin目录开始
			 *  所以不能使用点.
			 */
			//FileInputStream in = new FileInputStream("./src/db.properties");
			
			/**
			 * 使用类路径的读取方式
			 *  / : 斜杠表示classpath的根目录
			 *     在java项目下，classpath的根目录从bin目录开始
			 *     在web项目下，classpath的根目录从WEB-INF/classes目录开始
			 */
			InputStream in=JdbcUtil.class.getResourceAsStream("/db.properties");
			
			//加载文件
			props.load(in);
			//获取信息
			url=props.getProperty("url");
			user=props.getProperty("user");
			password=props.getProperty("password");
			driverClass=props.getProperty("driverClass");
			
			//加载驱动
			Class.forName(driverClass);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 抽取获取连接对象的方法
	 */
	public static Connection getConnection(){
		
		Connection conn=null;
			try {
				conn = DriverManager.getConnection(url,user,password);
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
	}
	
	/**
	 * 释放资源的方法
	 */
	public static void close(Connection conn,Statement stmt){
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void close (Connection conn,Statement stmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
