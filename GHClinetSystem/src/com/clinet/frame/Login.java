package com.clinet.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Login {

	private JFrame frame;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u767B\u5F55\u754C\u9762");
		frame.setBounds(700, 210, 850, 500);
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(570,130,120,70);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setBounds(570,210,120,70);
		frame.getContentPane().add(lblNewLabel_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		chckbxNewCheckBox.setBounds(652,230,120,30);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(570,260,180,25);
		frame.getContentPane().add(passwordField);
		
		btnNewButton = new JButton("\u786E\u8BA4");
		
		btnNewButton.setBounds(570,320,78,25);
		frame.getContentPane().add(btnNewButton);
		
		button = new JButton("\u53D6\u6D88");
		button.setBounds(660,320,78,25);
		frame.getContentPane().add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(570, 193, 180, 24);
		frame.getContentPane().add(comboBox);
		
		JButton btnMenu = new JButton("menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu t1=new menu();
				t1.setVisible(true);
			}
		});
		btnMenu.setBounds(719, 0, 113, 27);
		frame.getContentPane().add(btnMenu);
	}
}
