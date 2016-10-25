package cn.im.common;

import java.io.Serializable;

/**
 * 
 * Message
 * @author linCQ
 *
 */
public class Message implements Serializable {
	private String mesType = "0";// 定义1为验证成功，2为验证失败，3为普通信息类型
	private String sender;// 发送者
	private String receiver;// 收件人
	private String content;// 内容
	private String date;

	public String getMesType() {
		return mesType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}

}