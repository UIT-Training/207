package cn.im.common;

import java.io.Serializable;

/**
 * 对象在网络中存在 流之间的通信 必须需要序列化 
 * @author linCQ
 *
 */
public class User implements Serializable {
	private String count;// 帐号，密码
	private String psd;
	private String type = "login";// 默认账户类型是登录

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User(String count, String psd) {
		super();
		this.count = count;
		this.psd = psd;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}
}