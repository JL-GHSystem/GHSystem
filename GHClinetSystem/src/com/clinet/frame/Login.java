package com.clinet.frame;

import javax.swing.*;
import java.awt.*;

public class Login {
	
	    public static void main(String[] args){
	         JFrame frame=new JFrame("登录界面");
	         frame.setLayout(null);
	         JLabel j1=new JLabel("请输入用户名:");
	         JLabel j2=new JLabel("请输入密码:");
	         JButton jb1=new JButton("确定");
	         JButton jb2=new JButton("取消");
	         JCheckBox jcb1=new JCheckBox("记住密码");
	         JTextField jTextField=new JTextField();
	         JPasswordField jPasswordField=new JPasswordField();
	         frame.setSize(850, 500);
	         j1.setBounds(570,130,120,70);
	         jTextField.setBounds(570, 180, 180, 25);
	         j2.setBounds(570,210,120,70);
	         jPasswordField.setBounds(570,260,180,25);
	         jb1.setBounds(570,320,78,25);
	         jb2.setBounds(660,320,78,25);
	         jcb1.setBounds(652,230,120,30);
	         frame.add(j1);
	         frame.add(j2);
	         frame.add(jTextField);
	         frame.add(jPasswordField);
	         frame.add(jb1);
	         frame.add(jb2);
	         frame.add(jcb1);
	         frame.setVisible(true);
	     }   
}
