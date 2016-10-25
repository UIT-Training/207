import java.sql.*;

public class ConnMySql {
	public static void main(String[] args) throws Exception{
		
		String a1="*from";
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/QQ","root","123123");
		
//		System.out.println(conn);
		
		Statement stmt =conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("select "+a1+" User");
		
		while(rs.next()){
			
//			System.out.println(rs.getString("id")+"\t"+rs.getString("passwd"));
			String a="1";
			String ap="123456";
						
			if(a.equals(rs.getString("id"))){
				if(ap.equals(rs.getString("passwd"))){
					System.out.println("√‹¬Î’˝»∑!");
				}
			}
		}
	}
}
