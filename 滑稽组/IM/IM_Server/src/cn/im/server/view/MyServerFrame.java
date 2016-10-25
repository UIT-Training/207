package cn.im.server.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cn.im.common.Message;
import cn.im.common.MessageType;
import cn.im.common.User;
import cn.im.server.model.ImServer;
import cn.im.server.model.ManageClientThread;


/**
 * 
 * 
 * @author linCQ
 *
 */
public class MyServerFrame extends JFrame implements ActionListener {
	JPanel jp1, jp2;
	JLabel jlb1, jlb2;
	JTextField jtf;
	JTextArea jta;
	JScrollPane jsp;
	JButton jb1, jb2, jb3;// 关闭服务器，强制下线，发送系统信息按钮
//	JButton jb4;
	

	public MyServerFrame() {
		jlb1 = new JLabel(new ImageIcon("images/sign.jpg"));// 图片不显示
		jlb2 = new JLabel("系统公告");
		jp1 = new JPanel();
		jp2 = new JPanel();
		jtf = new JTextField(40);
		jb1 = new JButton("关闭服务器");
		jb2 = new JButton("当前用户在线状况");
		jb3 = new JButton("发送");
//		jb4 = new JButton("开启服务器");
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
//		jb4.addActionListener(this);

		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		this.add(jsp);

		jp2.add(jlb2);
		jp2.add(jtf);
		jp2.add(jb3);

		jp1.add(jlb1);
//		jp1.add(jb4);//开启服务器
		jp1.add(jb1);
		jp1.add(jb2);
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.SOUTH);
		this.setIconImage(new ImageIcon("image/head.jpg").getImage());
		this.setVisible(true);
		this.setBounds(300, 200, 600, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MyServerFrame();
		new ImServer();
		
	}

	public void offAll()// 通知所有用户下线
	{
		Map threadPool = ManageClientThread.threadPool;
		Iterator it = threadPool.keySet().iterator();
		Message message = new Message();
		message.setMesType(MessageType.off_line);
		while (it.hasNext()) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientThread.getClientThread(it.next().toString()).getSocket().getOutputStream());
				oos.writeObject(message);
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == jb1) {
			try {
				if (!ImServer.server.isClosed()) {
					ImServer.server.close();// 关闭服务器,用户无法继续登陆
					offAll();
					JOptionPane.showMessageDialog(this, "服务器关闭成功");
				} else {
					JOptionPane.showMessageDialog(this, "服务器没有连接");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == jb2)// 获取当前用户状态
		{
			jta.setText("");
			List<User> list = ImServer.state();
			Map threadPool = ManageClientThread.threadPool;
			for (User user : list) {
				if (threadPool.get(user.getCount()) != null) {
					jta.append("当前状态：在线                 " + "用户帐号：" + user.getCount() + "\t\t" + "用户密码：" + user.getPsd()
							+ "\n");
				} else
					jta.append("当前状态：离线                  " + "用户帐号：" + user.getCount() + "\t\t" + "用户密码："
							+ user.getPsd() + "\n");
			}

		}
		else {// 发公告,发送下线原因
			Map threadPool = ManageClientThread.threadPool;
			Iterator it = threadPool.keySet().iterator();
			Message message = new Message();
			message.setMesType(MessageType.sys_mess);
			message.setContent("系统公告：" + jtf.getText() + "，谢谢！！！\n");
			while (it.hasNext()) {
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientThread.getClientThread(it.next().toString()).getSocket().getOutputStream());
					oos.writeObject(message);
					// break;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}