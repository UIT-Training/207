package client.view;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.model.ClientConServer;
import client.tools.ManagerClientConServerThread;
import common.Message;
import common.User;
import sun.net.www.content.image.jpeg;

public class Chat extends JFrame implements ActionListener{

	
	
	JTextArea jTextArea;
	JTextField jTextField;
	JButton jButton;
	JPanel jPanel;
	String ownerID;
	String friendID;
	
	public static void main(String[] args){
//		Chat chat = new Chat("测试");
	}
	
	public Chat(String ownerID,String friendID){
		this.ownerID = ownerID;
		this.friendID = friendID;
		jTextArea = new JTextArea();
		jTextField = new JTextField(15);
		jButton = new JButton("发送");
		jButton.addActionListener(this);
		jPanel = new JPanel();
		
		jPanel.add(jTextField);
		jPanel.add(jButton);
		
		this.add(jTextArea,"Center");
		this.add(jPanel, "South");
		this.setTitle(ownerID+"正在和"+friendID+"聊天");
		this.setSize(300,200);
		this.setVisible(true);
	}
	
	public void showMessage(Message message){
		String info = message.getSender()+"对"+message.getGetter()+"说："+message.getCon()+"\r\b\n";
		this.jTextArea.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton) {
			//如果用户点击了发送按钮
			Message message = new Message();
			message.setSender(this.ownerID);
			message.setGetter(this.friendID);
			message.setCon(jTextField.getText());
			message.setSendtime(new Date().toString());
			//发送message给服务器
			try {
				
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManagerClientConServerThread.getClientConServerThread(ownerID).getSocket().getOutputStream());
				objectOutputStream.writeObject(message);
				
				String info = message.getGetter()+"对"+message.getSender()+"说："+message.getCon()+"\r\b\n";
				this.jTextArea.append(info);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}

}
