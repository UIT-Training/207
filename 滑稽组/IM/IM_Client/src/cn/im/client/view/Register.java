package cn.im.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;

import cn.im.common.Message;
import cn.im.common.MessageType;
import cn.im.common.User;


/**
 * 视图：注册
 * @author linCQ
 *
 */
public class Register extends JFrame implements ActionListener {
	JButton send;
	JButton cancle;
	JPanel jp1;
	JPanel jp2;
	JPanel jp3;
	JPanel jp4;
	JPanel jp5;
	private String username;
	private String password;

	JTextField jtf_username;
	JPasswordField jpf_password;

	public static void main(String[] args) {
		new Register();
	}

	public Register() {
		jtf_username = new JTextField(15);
		jpf_password = new JPasswordField(15);
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp5.add(new JLabel("请输入注册信息:                                             "));
		jp1 = new JPanel();

		jp3.add(new JLabel("用户名："));
		jp3.add(jtf_username);

		jp4.add(new JLabel("密码：    "));
		jp4.add(jpf_password);

		BoxLayout lo = new BoxLayout(jp1, BoxLayout.Y_AXIS);
		jp1.setLayout(lo);
		jp1.add(jp5);
		jp1.add(jp3);
		jp1.add(jp4);

		jp2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		cancle = new JButton("取消");
		send = new JButton("确定");
		cancle.addActionListener(this);
		send.addActionListener(this);
		jp2.add(cancle);
		jp2.add(send);

		this.setIconImage(new ImageIcon("image/head.jpg").getImage());
		this.setLayout(new BorderLayout());
		this.add(new JLabel(new ImageIcon("image/logo.jpg")), "North");
		this.add(jp1, "Center");
		this.add(jp2, "South");
		this.setSize(430, 330);
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle("IM注册");
		this.setLocation(900, 250);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send) {
			User user = new User(jtf_username.getText(), new String(jpf_password.getPassword()));
			user.setType("register");
			try {
				Socket ss = new Socket("127.0.0.1", 8888);// 注册端连接服务器
				ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
				oos.writeObject(user);// 将用户注册信息发送给服务器

				ObjectInputStream ois = new ObjectInputStream(ss.getInputStream());
				Message reg = (Message) ois.readObject();// 注册之后的反馈信息

				if (reg.getMesType().equals(MessageType.regist_succeed)) {
					JOptionPane.showMessageDialog(this, "注册成功");
				} else
					JOptionPane.showMessageDialog(this, "注册失败");

			} catch (Exception e1) {
//				e1.printStackTrace();
				JOptionPane.showConfirmDialog(this,"连接服务器失败。。。请检查网络设置.");
				throw new RuntimeException();
			}
		} else {
			this.dispose();
		}

	}
}