
//功能：客户端登录界面
package client.view;

import javax.swing.*;

import client.model.ClientUser;
import common.User;

import java.awt.*;
import java.awt.event.*;
import java.awt.FlowLayout;
public class ChatClientLogin extends JFrame implements ActionListener{
	
	//定义北部需要的组件
	JLabel jbl1;
	//定义中部需要的组件
	
	JTabbedPane jtp;
	JPanel jp2;
	JLabel jp2_jbl1,jp2_jbl2;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	
	
	//定义南部需要的组件
	JPanel jp1;
	JButton jb1;

	public static void main(String[] args) {
		
		ChatClientLogin qqClientLogin=new ChatClientLogin();
		

	}
	public ChatClientLogin(){
		
		jbl1=new JLabel(new ImageIcon("image/background.jpeg"));
		//处理中部
		jp2=new JPanel (new GridLayout(2,2));
		jp2_jbl1=new JLabel("User",JLabel.CENTER);
		jp2_jbl2=new JLabel("PassWord",JLabel.CENTER);
		jp2_jtf=new JTextField(JLabel.CENTER);
		jp2_jpf=new JPasswordField(JLabel.CENTER);
		//把控件按顺序加入到jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		
		//处理南部
		jp1=new JPanel(new FlowLayout());
		jb1=new JButton("                    Login                   ");
		jb1.addActionListener(this);
		//把按钮放入jb1
		jp1.add(jb1);
		
		this.add(jbl1,"North");
		this.add(jp2,"Center");
		this.add(jp1,"South");
	   
		this.setSize(450,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == jb1) {
			
			ClientUser clientUser = new ClientUser();
			User user = new User();
			user.setUserID(jp2_jtf.getText().trim());
			user.setPassword(new String(jp2_jpf.getPassword()));
			
			if (clientUser.checkUser(user)) {
				
				new FriendList(user.getUserID());
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "用户名密码错误");
			}
			
		}
		
	}

}
