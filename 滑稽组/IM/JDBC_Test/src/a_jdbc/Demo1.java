package a_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

/**
 * jdbc 连接数据库
 * 
 * 加载 jdbc驱动 三种方法
 * @author linCQ
 *
 */
public class Demo1 {
	
	//连接数据库的url
	private String url="jdbc:mysql://127.0.0.1:3306/jdbc_test";
					//jdbc协议:数据库子协议://主机:端口/连接的数据库
	private String user="root";
	private String password="1071895900";
	
	/**
	 * 使用加载程序驱动类  来 注册驱动程序
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		
		//通过得到的字节码对象 的方式 加载静态代码块,从而注册驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		
		//获取数据库连接
		Connection conn=DriverManager.getConnection(url,user,password);
		System.out.println(conn);
		
	}
}
