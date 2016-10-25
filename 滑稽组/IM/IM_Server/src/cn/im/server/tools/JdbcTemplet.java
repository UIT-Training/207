package cn.im.server.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.im.server.db.ResultSetHandler;


/**
 * JDBC 数据库实现增删查 模板
 * @author linCQ
 *
 */
public class JdbcTemplet {
	private Connection conn= null;
	private PreparedStatement ps= null;
	private ResultSet rs= null;
	
/*	static{//建表操作
		try {
			PreparedStatement ps= JDBCUtil.getConnection().prepareStatement("create table users (id int(10) primary key auto_increment, count varchar(30), psd varchar(30))");
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/**
	 * 增删改模板
	 * @param sql
	 * @param args
	 */
	public void update(String sql, Object...args)
	{	
		try {
			conn= JdbcUtil.getConnection();
			ps= conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]); 
			}
			ps.executeUpdate();//更新
		} catch (SQLException e) {
			// TODO: handle exception
		}finally{
			JdbcUtil.close(conn, ps, rs);
		}
	}
	
	/**
	 * 查询模板
	 * 
	 * @param sql
	 * @param handler
	 * @param args
	 * @return
	 */
	public Object query(String sql, ResultSetHandler handler, Object... args)// 查询模板
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();// 连接数据库
			ps = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i + 1, args[i]);
				}
			}
			rs = ps.executeQuery();
			return handler.doHandler(rs);// 返回对结果集rs处理后的结果
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;// 返回一个空
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
	}
}
