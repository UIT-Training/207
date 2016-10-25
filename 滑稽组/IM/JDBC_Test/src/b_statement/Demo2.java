package b_statement;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;



import util.JdbcUtil;

/**
 * 使用Statement 执行DML语句
 * @author linCQ
 *
 */
public class Demo2 {
//	private String url="jdbc:mysql://localhost:3306/jdbc_test";
//	private String user="root";
//	private String password="1071895900";
	
	/**
	 * 添加/插入
	 */
	@Test
	public void testInsert(){
		Connection conn=null;
		Statement stmt=null;
		
		try{
			//通过工具类获取连接对象
			conn=JdbcUtil.getConnection();
			
			//创建Statement对象
			stmt=conn.createStatement();
			
			//准备sql语句
			String sql="INSERT INTO student(NAME,gender) VALUES('李四','女')";
			
			//执行sql
			int count =stmt.executeUpdate(sql);
			
			System.out.println("影响了"+count+"行");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			//关闭资源
			/*if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}*/
			JdbcUtil.close(conn, stmt);
		}
	}
	
	/**
	 * 修改
	 */
	@Test
	public void testUpdate(){
		Connection conn=null;
		Statement stmt=null;
		//模拟用户输入
		String name="陈六";
		int id=3;
		
		try{
			//加载驱动
			//Class.forName("com.mysql.jdbc.Driver");
			conn=JdbcUtil.getConnection();
			
			//创建Statement对象
			stmt=conn.createStatement();
			
			//sql语句
			String sql = "update student set Name='"+name+"'where id="+id+"";
			
			System.out.println(sql);
			
			//执行sql
			int count = stmt.executeUpdate(sql);
			
			System.out.println("影响了"+count+"行");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			//关闭资源
			JdbcUtil.close(conn, stmt);
		}
	}
	
	/**
	 * 删除
	 */
	@Test
	public void testDelete(){
		Connection conn=null;
		Statement stmt=null;
		//模拟用户输入
		int id=3;
		try{
			//通过工具类获取数据库连接对象
			conn=JdbcUtil.getConnection();
			
			//创建Statement 对象
			stmt=conn.createStatement();
			
			//sql
			String sql="delete from student where id="+id+"";
			
			//执行sql
			int count = stmt.executeUpdate(sql);
			
			System.out.println("影响了"+count+"行");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JdbcUtil.close(conn, stmt);
		}
	}
	
	
	
	
	
	
	
	
	

	
	
}
