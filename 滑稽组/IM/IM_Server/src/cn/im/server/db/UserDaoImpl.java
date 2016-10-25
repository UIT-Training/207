package cn.im.server.db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.im.common.User;
import cn.im.server.tools.JdbcTemplet;


/**
 * 实现UserDao接口
 * 
 * @author linCQ
 *
 */
public class UserDaoImpl implements UserDao {
	
	private JdbcTemplet jTemplet= new JdbcTemplet();//创建模版对象
	
	@Override
	public void add(User u) throws SQLException {//增
		// TODO Auto-generated method stub
		jTemplet.update("insert into users (count, psd) values(?,?)", u.getCount(),u.getPsd());
		System.out.println("添加成功！");
	}

	public void delete(String count) throws SQLException {//删
		// TODO Auto-generated method stub
		jTemplet.update("delete from users where count= ?", count);
		System.out.println("删除成功！");
	}

	@Override
	public void update(User u) throws SQLException {//改
		// TODO Auto-generated method stub
		jTemplet.update("update users set count= ?, psd= ? where count= ?",u.getCount(),u.getPsd(),u.getCount());
		System.out.println("修改成功！");
	}
	
	@Override
	public User queryByCount(final String count) throws SQLException {// 查询单个对象

		String sql = "select count, psd from users where count=  ?";
		return (User) jTemplet.query(sql, new ResultSetHandler() {
			@Override
			public Object doHandler(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				User user = null;
				if (rs.next()) {
					user = new User(rs.getString(1), rs.getString(2));
				}
				return user;
			}
		}, count);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryAll() throws SQLException {//查询所有记录
		String sql= "select count,psd from users";
		return (List<User>)jTemplet.query(sql, new ResultSetHandler() {//强转 list集合
			
			@Override
			public Object doHandler(ResultSet rs) throws SQLException {//匿名内部类实现接口  作为一个参数传入函数
				// TODO Auto-generated method stub
				List<User> users= new ArrayList<User>();
				User user= null;
				while(rs.next()) 
				{
					user= new User(rs.getString(1),rs.getString(2));
					users.add(user);
				}
				return users;
			}
		});
	}
}
