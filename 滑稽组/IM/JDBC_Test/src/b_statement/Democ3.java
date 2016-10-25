package b_statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

/**
 * 使用Statement 执行DQL语句(查询操作）
 * @author linCQ
 *
 */
public class Democ3 {
	
	@Test
	public void test1(){
		Connection conn=null;
		Statement stmt=null;
		try{
			//获取连接
			conn=JdbcUtil.getConnection();
			//创建Statement对像
			stmt=conn.createStatement();
			//准备sql语句
			String sql="select *from student";
			//执行sql
			ResultSet rs=stmt.executeQuery(sql);
			
			//遍历结果
			
			/*iboolean flag=rs.next();
			
			f(flag){
				//取出索引值
				//索引
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String gender=rs.getString(3);
				System.out.println(id+" "+name+" "+gender);
				
				//列名称
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				System.out.println(id+","+name+","+gender);
				
			}*/
			
			
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String gender=rs.getString("gender");
				
				System.out.println(id+" "+name+" "+gender);
			}
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			JdbcUtil.close(conn, stmt);
		}
		
	}

}
