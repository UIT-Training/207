package cn.gongly.client.view;

/**
 * 
 * @author MSI
 *我的好友列表
 */
import javax.swing.*;

import cn.gongly.client.tools.ManageQqchat;
import cn.gongly.common.Message;

import java.awt.*;
import java.awt.event.*;

public class QqFriendList extends JFrame implements ActionListener, MouseListener {
	// 处理卡片
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1;
	JScrollPane jsp1;
	String owner;
	JLabel[] jbls;

	public static void main(String[] args) {
		// QqFriendList QqFriendList = new QqFriendList();
	}

	// 更新在线好友情况
	public void updateFriend(Message m) {
		String onLineFriend[] = m.getCon().split(" ");
		for (int i = 0; i < onLineFriend.length; i++) {
			jbls[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}
	}

	public QqFriendList(String ownerid) {
		jphy1 = new JPanel(new BorderLayout());
		// 假定20个好友
		jphy2 = new JPanel(new GridLayout(20, 1, 4, 4));
		// 初始化20好友
		jbls = new JLabel[20];

		for (int i = 0; i < jbls.length; i++) {
			jbls[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			jbls[i].setEnabled(false);
			if (jbls[i].getText().equals(ownerid)) {
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);

		}
		this.owner = ownerid;
		jphy_jb1 = new JButton("我的好友");
		jphy_jb1.addActionListener(this);
		jsp1 = new JScrollPane(jphy2);

		// 最大
		jphy1.add(jphy_jb1, "North");
		jphy1.add(jsp1, "Center");

		this.add(jphy1, "Center");

		this.setTitle(ownerid);
		this.setSize(160, 400);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 响应用户双击,得到好友编号
		if (e.getClickCount() == 2) {
			// 得到该好友的编号
			String friendNo = ((JLabel) e.getSource()).getText();
			// System.out.println("你希望和"+friendNo+"聊天");
			QqChat qqChat = new QqChat(this.owner, friendNo);
			// Thread t =new Thread(qqChat);
			// t.start();
			// 把聊天界面加入到管理类
			ManageQqchat.addQqChat(this.owner + " " + friendNo, qqChat);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.RED);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.BLACK);
	}
}
