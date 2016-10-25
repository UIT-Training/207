package cn.im.server.db;

import java.sql.SQLException;
import java.util.List;

import cn.im.common.User;


/**
 * 定义规范： 增删改查 
 * @author linCQ
 *
 */
public interface UserDao {
	public void add(User u) throws SQLException;//增
	public void delete(String count) throws SQLException;//删
	public void update(User u) throws SQLException;//改
	public User queryByCount(String count) throws SQLException;//查一个
	public List<User> queryAll() throws SQLException;//查询全部信息，List集合 可重复，有序。是collection的特性
}
