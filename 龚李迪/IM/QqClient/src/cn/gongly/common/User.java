package cn.gongly.common;

/**
 * �û���Ϣ
 * 
 * @author MSI
 *
 */
public class User implements java.io.Serializable {

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	private String userID;
	private String passwd;

}
