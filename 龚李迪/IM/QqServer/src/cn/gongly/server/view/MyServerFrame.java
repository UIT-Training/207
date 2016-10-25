package cn.gongly.server.view;

import cn.gongly.server.model.MyQqServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * 这是服务器端的控制界面，可以完成启动服务器，关闭服务器 管理在线用户
 * 
 * @author MSI
 *
 */

public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1, jb2;
	JTextArea jta;

	public static void main(String[] args) {
		MyServerFrame mysf = new MyServerFrame();
	}

	public MyServerFrame() {

		jp1 = new JPanel();// 初始化容器
		jb1 = new JButton("启动服务器");// 初始化按钮
		jb1.addActionListener(this);// 对按钮进行监听
		jb2 = new JButton("关闭服务器");
		jb2.addActionListener(this);
		jta = new JTextArea();
		

		jp1.add(jb1);// 把按钮加入容器
		jp1.add(jb2);
		
		this.add(jta);
		this.add(jp1,"North");// 把容器加入界面

		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使用 System exit
															// 方法退出应用程序。仅在应用程序中使用。
															// 也就是说没有设置的话,默认点关闭时只是隐藏窗体,在后台进程中还可以看到
		this.setVisible(true);
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == jb1) {
			new MyQqServer().start();
		} else if (e.getSource() == jb2) {
			this.dispose();
			System.exit(0);
		}
	}
	
	public void showMessage(String id,String pw){
		
		String sum = "服务器接收到用户"+id+"密码"+pw+new java.util.Date().toString();
		this.jta.append(sum);
	}
	
}


