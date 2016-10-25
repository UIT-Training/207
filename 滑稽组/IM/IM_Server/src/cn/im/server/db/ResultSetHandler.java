package cn.im.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 
 * @author linCQ
 *
 */
public interface ResultSetHandler {
	
	abstract Object doHandler(ResultSet rs) throws SQLException;

}
