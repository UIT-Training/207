package cn.im.common;

/**
 * 利用接口定义final常量
 * 
 * @author linCQ
 *
 */
public interface MessageType {
	String login_succeed = "1";// 登录成功 1
	String login_fail = "2"; //登录失败  2
	String common_mess = "3";
	String get_onLineFriend = "4";
	String re_onLineFriend = "5";
	String regist_succeed = "6";//注册成功  6
	String regist_fail = "7";//注册失败  7
	String off_line = "8";//下线  8
	String sys_mess = "9";//接受系统信息  9
}