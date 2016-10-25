package cn.im.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cn.im.client.tools.ManageImChat;
import cn.im.common.Message;


/**
 * 
 * 视图：FriendList
 * 
 * @author linCQ
 *
 */
public class FriendList extends JFrame implements ActionListener,MouseListener{
	
	private JPanel jphy,jpms,jphm;//三个panel，分别是好友，陌生人，黑名单三个卡片
	private JButton hyjb1,hyjb2,hyjb3;// 好友卡片的 好友，陌生人，黑名单三个按钮
	private JButton msjb1,msjb2,msjb3;// 陌生人卡片的 好友，陌生人，黑名单
	
	private JPanel hyjp1,hyjp2;
	private JPanel msjp1,msjp2;
	private JScrollPane hyjsp,	msjsp;
	private JLabel[] hyjls, msjls;
	private CardLayout cl;
	private String username;
	
	public FriendList(String userId){
		/**
		 * 第一 个页面布局
		 */
		this.username= userId;
		jphy= new JPanel(new BorderLayout());
		hyjb1= new JButton("好友");
		hyjb1.addActionListener(this);
		hyjb2= new JButton("陌生人");
		hyjb2.addActionListener(this);
		hyjb3= new JButton("黑名单");
		jphy.add(hyjb1,"North");
		
		hyjp1= new JPanel(new GridLayout(50,1,4,4));
		hyjls=new JLabel[50];
		for (int i = 0; i < hyjls.length; i++) {
			hyjls[i]= new JLabel(i+1+"",new ImageIcon("image/head.jpg"),JLabel.LEFT);
			hyjp1.add(hyjls[i]);
			if(!(hyjls[i].getText().equals(username)))
				hyjls[i].setEnabled(false);//只要不是 全部设置为灰色
			
			hyjls[i].addMouseListener(this);
		}
		hyjsp= new JScrollPane(hyjp1);
		jphy.add(hyjsp);
		
		hyjp2= new JPanel(new GridLayout(2,1));
		hyjp2.add(hyjb2);	hyjp2.add(hyjb3);
		jphy.add(hyjp2,BorderLayout.SOUTH);
		
		/*
		 * 布局第二个卡片
		 */
		jpms= new JPanel(new BorderLayout());
		msjb1= new JButton("好友");
		msjb2= new JButton("陌生人");
		msjb3= new JButton("黑名单");
		msjb1.addActionListener(this);
		
		msjp1= new JPanel(new GridLayout(2,1));
		msjp1.add(msjb1);	msjp1.add(msjb2);
		jpms.add(msjp1,BorderLayout.NORTH);
		
		
		jpms.add(msjb3,BorderLayout.SOUTH);
		
		msjp2= new JPanel(new GridLayout(50,1,4,4));
		msjls=new JLabel[50];//定义一个标签数组用来存放陌生人们
		for (int i = 0; i < msjls.length; i++) {
			msjls[i]= new JLabel(i+1+"",new ImageIcon("image/head.jpg"),JLabel.LEFT);//设置图片在文字左侧
			msjp2.add(msjls[i]);
			if(!(msjls[i].getText().equals(username)))
				msjls[i].setEnabled(false);//只要不是 ，全部设置为灰色
			
			msjls[i].addMouseListener(this);
		}
		
		msjsp= new JScrollPane(msjp2);//不能通过add给jsp里加入内容
		jpms.add(msjsp,BorderLayout.CENTER);

		cl= new CardLayout();
		this.setLayout(cl);
		this.add(jphy,"1");//添加第一个卡片，并标记1
		this.add(jpms,"2");
		this.setTitle(userId);
		this.setIconImage(new ImageIcon("image/head.jpg").getImage());
		this.setVisible(true);
		this.setBounds(1100, 100, 250, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void updateFriendList(Message mess)//包含有当前在线用户的包
	{
		System.out.println("刷新了");
		String content= mess.getContent();
		String[] friend= content.split(" ");//空格拆分得到一个当前在线好友组
		for (int i = 0; i < friend.length; i++) {
			hyjls[(Integer.parseInt(friend[i])-1)].setEnabled(true);
			msjls[(Integer.parseInt(friend[i])-1)].setEnabled(true);
		}//刷新完毕
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2)
		{
			String friendName= ((JLabel)e.getSource()).getText();
			ManageImChat.addImChat(username+" "+friendName, new ImChat(username, friendName));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==msjb1)
		{
			cl.show(this.getContentPane(), "1");
		}else if(e.getSource()==hyjb2)
		{
			cl.show(this.getContentPane(), "2");
		}
	}
}
