package cn.gongly.client.view;

import javax.swing.*;

import cn.gongly.client.model.QqClineUser;
import cn.gongly.client.tools.ManageQqFriendList;
import cn.gongly.client.tools.ManagerClientConServerThread;
import cn.gongly.common.Message;
import cn.gongly.common.MessageType;
import cn.gongly.common.User;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import cn.gongly.common.*;

/**
 * 
 * @author GLD 功能:QQ登陆界面
 *
 */

public class QqclientLogin extends JFrame implements ActionListener {

	// 定义北部
	JLabel jbl1;

	// 定义中部
	JPanel jp2;
	JLabel jp2_jbl1, jp2_jbl2;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;

	// 定义南部
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;

	public static void main(String[] args) {
		QqclientLogin QqclientLogin = new QqclientLogin();
	}

	public QqclientLogin() {
		// 处理北边
		jbl1 = new JLabel(new ImageIcon("image/tou.gif"));

		// 处理中部
		jp2 = new JPanel(new GridLayout(2, 2, 15, 15));
		jp2_jbl1 = new JLabel("号码", JLabel.CENTER);
		jp2_jbl2 = new JLabel("密码", JLabel.CENTER);
		jp2_jtf = new JTextField(15);
		jp2_jpf = new JPasswordField(15);

		// 加入JP2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);

		// 处理南部
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("image/denglu.gif"));

		// 响应用户登陆
		jp1_jb1.addActionListener(this);

		jp1_jb2 = new JButton(new ImageIcon("image/quxiao.gif"));
		jp1_jb3 = new JButton(new ImageIcon("image/xiangdao.gif"));

		// 放入jp1
		jp1.add(jp1_jb1);
		// jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);

		this.add(jbl1, "North");
		this.add(jp2, "Center");
		this.add(jp1, "South");

		this.setSize(350, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jp1_jb1) {
			QqClineUser qqClientUser = new QqClineUser();
			User u = new User();
			u.setUserID(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));

			if (qqClientUser.checkUser(u)) {
				// 返回好友在线的包
				try {
					// 把创建好友列表的语句提前
					QqFriendList qqList = new QqFriendList(u.getUserID());
					ManageQqFriendList.addQqFriendList(u.getUserID(), qqList);

					// 发送一个要求返回在线好友的请求包.
					ObjectOutputStream oos = new ObjectOutputStream(ManagerClientConServerThread
							.getClientConServerThread(u.getUserID()).getS().getOutputStream());

					Message m = new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					// 指明我要的是这个qq号的好友情况.
					m.setSender(u.getUserID());
					oos.writeObject(m);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// 同时关闭登陆界面
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "用户名密码错误");
			}
		}
	}
}
