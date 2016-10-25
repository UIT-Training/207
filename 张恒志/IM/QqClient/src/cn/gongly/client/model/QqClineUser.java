package cn.gongly.client.model;

import cn.gongly.common.*;

public class QqClineUser {

	public boolean checkUser(User u) {
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
}
