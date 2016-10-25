package cn.gongly.client.view;

/**
 * 聊天界面
 * @author MSI
 *
 */

import javax.swing.*;

import cn.gongly.common.Message;
import cn.gongly.common.MessageType;
import cn.gongly.client.model.*;
import cn.gongly.client.tools.ManagerClientConServerThread;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class QqChat extends JFrame implements ActionListener {

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;

	public static void main(String[] args) {
		// QqChat QqChat = new QqChat("1");
	}

	public QqChat(String ownId, String friand) {

		this.ownerId = ownId;
		this.friendId = friand;

		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("发送");
		jb.addActionListener(this);
		jp = new JPanel();

		jp.add(jtf);
		jp.add(jb);

		this.add(jta);
		this.add(jp, "South");

		this.setTitle(ownId + "正在和" + friand + "聊天");
		this.setIconImage((new ImageIcon("image/qq.gif")).getImage());

		this.setSize(300, 200);
		this.setVisible(true);

	}

	// 显示消息
	public void showMessage(Message m) {
		String info = m.getSender() + "对" + m.getGetter() + "说" + m.getCon() + "\r\n";// 可以加时间
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb) {
			// 如果用户点击了发送
			
			String info2="你说:"+jtf.getText()+ "\r\n";
			this.jta.append(info2);
			Message m = new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendtime(new java.util.Date().toString());

			// 发送给服务器

			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManagerClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				this.jtf.setText("");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

	// public void run() {
	// while(true){
	// //读取(如果读不到,就一直等待)
	// try {
	//
	//
	// ObjectInputStream ois =new
	// ObjectInputStream(QqClientConServer.s.getInputStream());
	// Massage m = (Massage)ois.readObject();
	// String info =
	// m.getSender()+"对"+m.getGetter()+"说"+m.getCon()+"\r\n";//可以加时间
	// this.jta.append(info);
	//
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }

}
