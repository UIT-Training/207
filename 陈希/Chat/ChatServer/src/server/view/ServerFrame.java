package server.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import server.model.Server;

public class ServerFrame extends JFrame implements ActionListener{

	JPanel jpanel1;
	JButton jButton1,jButton2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerFrame serverFrame = new ServerFrame();
	}
	
	public ServerFrame(){
		jpanel1 = new JPanel();
		jButton1 = new JButton("启动服务器");
		jButton1.addActionListener(this);
		jButton2 = new JButton("关闭服务器");
		jpanel1.add(jButton1);
		jpanel1.add(jButton2);
		
		this.add(jpanel1);
		this.setSize(200,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jButton1) {
			Server server = new Server();
		}
	}


	
}
