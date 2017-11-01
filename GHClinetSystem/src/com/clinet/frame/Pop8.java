package com.clinet.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Pop8 {

	private JFrame frame;
	private JTextField textField;
	private JLabel label;
	private JTextField textField_1;
	private JLabel label_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pop8 window = new Pop8();
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
	public Pop8() {
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
		frame.setTitle("\u4FEE\u6539\u5BC6\u7801");
		frame.setBounds(700, 210, 419, 271);
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u65E7\u5BC6\u7801");
		lblNewLabel.setBounds(48, 35, 53, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(124, 32, 157, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label = new JLabel("\u65B0\u5BC6\u7801");
		label.setBounds(48, 76, 53, 18);
		frame.getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(124, 73, 157, 24);
		frame.getContentPane().add(textField_1);
		
		label_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_1.setBounds(48, 113, 60, 18);
		frame.getContentPane().add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(124, 110, 157, 24);
		frame.getContentPane().add(textField_2);
		
		btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.setBounds(168, 166, 90, 27);
		frame.getContentPane().add(btnNewButton);
		
		button = new JButton("\u53D6\u6D88");
		button.setBounds(272, 166, 90, 27);
		frame.getContentPane().add(button);
	}

}
