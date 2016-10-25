/**
 * 好友列表
 * @author chenxi
 *
 */
package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.tools.ManagerChat;
import common.User;

public class FriendList extends JFrame implements MouseListener{

	
	JPanel jphy,jphy_friendlist;
	JButton jphy_jb_freidnlist;
	JScrollPane jphy_jsp_friendlist;
	String ownerID;
	
	
	public static void main(String[] args) {
//		FriendList friendList = new FriendList();
	}
	
	public FriendList(String ownerID){
		
		this.ownerID = ownerID;
		jphy = new JPanel(new BorderLayout());
		jphy_jsp_friendlist = new JScrollPane(jphy_friendlist);

		jphy_friendlist = new JPanel(new GridLayout(10, 1 ,4 ,4));
		
		JLabel []jbls = new JLabel[10];
		
		for(int i=0;i<jbls.length;i++){
			jbls[i] = new JLabel(i+1+"",new ImageIcon("image/avatar.jpg"),JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jphy_friendlist.add(jbls[i]);
		}
		
		jphy_jb_freidnlist = new JButton("好友列表");
		
		
		jphy.add(jphy_jb_freidnlist,"North");
		jphy.add(jphy_friendlist,"Center");
		jphy.add(jphy_jsp_friendlist,"South");
		this.setTitle(ownerID);
		this.add(jphy,"Center");
		this.setSize(200,400);
		this.setVisible(true);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//响应用户双击的时间，并得到好友的编号
		if (e.getClickCount() == 2) {
			//得到好友的编号
			String friendID = ((JLabel)e.getSource()).getText();
			Chat chat = new Chat(ownerID,friendID);
			
			//把聊天界面加入到管理类
			
			ManagerChat.addChat(this.ownerID+" "+friendID, chat);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
