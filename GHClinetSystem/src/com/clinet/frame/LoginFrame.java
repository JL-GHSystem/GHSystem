package com.clinet.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.clinet.controller.TayberController;
import com.clinet.factory.TayberFactory;

public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private TayberController tayberController;
	private JButton jbutton = new JButton();

	public LoginFrame() {
		// TODO Auto-generated constructor stub
		tayberController = TayberFactory.create();
		
		tayberController.show();
		
		jbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginFrame.check("Frich", "0");
				tayberController.close();
			}
		});
		
	}
	
	public static boolean check(String account, String passwords) {
		if("Frich".equals(account) && "0".equals(passwords)) {
			return true;
		}
		return false;
	}
	
	
}
