package cn.im.client.control;

import cn.im.common.User;

/**
 * 检测登录
 * @author linCQ
 *
 */
public class ImClientUser {
	public boolean check(User user) {
		return new ImClientConServer().sendLoginInfoToServer(user);
	}
}
