package cn.im.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

import cn.im.client.control.ImClientUser;
import cn.im.client.tools.ManageClientConServerThread;
import cn.im.client.tools.ManageFriendList;
import cn.im.common.Message;
import cn.im.common.MessageType;
import cn.im.common.User;

import javax.swing.*;


/**
 * 视图：登录
 * 
 * @author linCQ
 *
 */
public class Login extends JFrame {

	private JLabel headLabel, luser, lpwd, lrg, lfg, loginlabel;
	private JPanel panel1, panel2, panel;
	private JPanel southPanel;
	private JTextField users;
	private JPasswordField pwd;

	public Login() {// 初始化登录界面
		this.setTitle("IM登录");
		this.setLocation(600, 300);

		// 头
		headLabel = new JLabel(new ImageIcon("image/logo.jpg"));
		// 中间
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		panel1 = new JPanel();
		panel2 = new JPanel();
		luser = new JLabel("账号:", JLabel.CENTER);

		lpwd = new JLabel("密码:", JLabel.CENTER);
		lrg = new JLabel("注册账号", JLabel.CENTER);

		lrg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 鼠标点击
				new Register();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// 鼠标进入
				lrg.setForeground(Color.decode("0x87CEFA"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 鼠标移除
				lrg.setForeground(Color.blue);
			}
		});

		lfg = new JLabel("找回密码", JLabel.CENTER);
		lfg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lfg.setForeground(Color.decode("0x87CEFA"));// 鼠标进入
			}

			@Override
			public void mouseExited(MouseEvent e) {// 鼠标移除

				lfg.setForeground(Color.blue);
			}
		});

		lrg.setForeground(Color.blue);
		lfg.setForeground(Color.blue);

		users = new JTextField(15);// 默认长度15
		pwd = new JPasswordField(15);// 默认长度15

		panel1.add(luser);
		panel1.add(users);
		panel1.add(lrg);
		panel2.add(lpwd);
		panel2.add(pwd);
		panel2.add(lfg);

		// 南
		southPanel = new JPanel();
		loginlabel = new JLabel(new ImageIcon("image/login2.jpg"));

		// 处理登录按钮
		loginlabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 鼠标点击
				// 逻辑代码
				User user=new User(users.getText().trim(),new String(pwd.getPassword()));
				Boolean result=new ImClientUser().check(user);
				
				if(result)//验证登陆成功
				{
					closeFrame();
					FriendList friendList= new FriendList(user.getCount());//创建一个好友列表
					ManageFriendList.addFriendList(user.getCount(), friendList);
					
					try {
						Message message= new Message();//发送一个在线信息请求包
						message.setMesType(MessageType.get_onLineFriend);
						message.setSender(user.getCount());//设置包内信息
						
						ObjectOutputStream oos= new ObjectOutputStream(ManageClientConServerThread.getClientThread(user.getCount()).getSocket().getOutputStream());
						oos.writeObject(message);//利用自己的线程的socket来发对套的信息
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else JOptionPane.showMessageDialog(null, "密码错误");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// 鼠标进入
				lrg.setForeground(Color.decode("0x87CEFA"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 鼠标移除
				lrg.setForeground(Color.blue);
			}
		});

		southPanel.add(loginlabel);
		panel.add(panel1);
		panel.add(panel2);
		panel.setBackground(Color.decode("0xebf2f9"));

		this.setLayout(new BorderLayout());
		this.add(southPanel, "South");
		this.add(headLabel, "North");
		this.add(panel, "Center");

//		this.setIconImage(new ImageIcon("image/im.gif").getImage());
		this.setSize(430, 330);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void closeFrame(){
		this.dispose();
	}

	public static void main(String[] args) {
		Login l = new Login();
	}

}
