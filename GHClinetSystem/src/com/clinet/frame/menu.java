package com.clinet.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public menu() {
		setTitle("\u7CFB\u7EDF\u521D\u59CB\u5316");
		
		setBounds(700, 210, 535, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u914D\u7F6E");
		lblNewLabel.setBounds(14, 13, 72, 18);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("内网");
		comboBox.addItem("国鸿外网");
		comboBox.addItem("默认");
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(88, 10, 187, 24);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("\u914D\u7F6E\u8BE6\u60C5");
		lblNewLabel_1.setBounds(14, 44, 72, 18);
		lblNewLabel_1.setBorder(BorderFactory.createEmptyBorder());
		lblNewLabel_1.setBorder(BorderFactory.createLineBorder(Color.blue));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u540D\u79F0");
		lblNewLabel_2.setBounds(50, 75, 41, 18);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(88, 72, 187, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.setBounds(289, 71, 63, 27);
		contentPane.add(btnNewButton);
		
		JButton button_1 = new JButton("\u5220\u9664");
		button_1.setBounds(436, 71, 63, 27);
		contentPane.add(button_1);
		
		JButton button = new JButton("\u4FEE\u6539");
		button.setBounds(363, 71, 63, 27);
		contentPane.add(button);
		
		JLabel lblNewLabel_3 = new JLabel("\u901A\u4FE1\u670D\u52A1\u5730\u5740");
		lblNewLabel_3.setBounds(31, 106, 103, 18);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("IP:");
		lblNewLabel_4.setBounds(50, 137, 41, 18);
		contentPane.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(88, 134, 187, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("\u7AEF\u53E3");
		label.setBounds(293, 137, 41, 18);
		contentPane.add(label);
		
		textField_2 = new JTextField();
		textField_2.setBounds(340, 134, 120, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_1 = new JLabel("\u57FA\u7840\u6570\u636E\u670D\u52A1\u5730\u5740");
		label_1.setBounds(31, 169, 120, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("IP:");
		label_2.setBounds(50, 194, 41, 18);
		contentPane.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(88, 191, 187, 24);
		contentPane.add(textField_3);
		
		JLabel label_3 = new JLabel("\u7AEF\u53E3");
		label_3.setBounds(293, 194, 41, 18);
		contentPane.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(340, 191, 120, 24);
		contentPane.add(textField_4);
		
		JLabel label_4 = new JLabel("\u5B50\u76EE");
		label_4.setBounds(31, 233, 41, 18);
		contentPane.add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(88, 230, 187, 24);
		contentPane.add(textField_5);
		
		JLabel label_5 = new JLabel("\u89C6\u9891\u670D\u52A1\u5730\u5740");
		label_5.setBounds(31, 271, 103, 18);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("IP:");
		label_6.setBounds(50, 301, 41, 18);
		contentPane.add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(88, 298, 187, 24);
		contentPane.add(textField_6);
		
		JLabel label_7 = new JLabel("\u7AEF\u53E3");
		label_7.setBounds(293, 301, 41, 18);
		contentPane.add(label_7);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(340, 298, 120, 24);
		contentPane.add(textField_7);
		
		JLabel label_8 = new JLabel("\u8C03\u5EA6\u670D\u52A1\u5730\u5740");
		label_8.setBounds(31, 344, 103, 18);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("IP:");
		label_9.setBounds(50, 375, 41, 18);
		contentPane.add(label_9);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(88, 375, 187, 24);
		contentPane.add(textField_8);
		
		JLabel label_10 = new JLabel("\u7AEF\u53E3");
		label_10.setBounds(293, 375, 41, 18);
		contentPane.add(label_10);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(340, 372, 120, 24);
		contentPane.add(textField_9);
		
		JLabel label_11 = new JLabel("\u5176\u4ED6\u5730\u5740");
		label_11.setBounds(31, 433, 103, 18);
		contentPane.add(label_11);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(101, 430, 359, 24);
		contentPane.add(textField_10);
		
		JLabel label_12 = new JLabel("\u7528\u6237");
		label_12.setBounds(14, 479, 72, 18);
		contentPane.add(label_12);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(50, 506, 173, 24);
		contentPane.add(comboBox_1);
		
		JButton button_2 = new JButton("\u6DFB\u52A0");
		button_2.setBounds(239, 505, 63, 27);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\u5220\u9664");
		button_3.setBounds(313, 505, 63, 27);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("\u8BBE\u7F6E");
		button_4.setBounds(272, 563, 103, 27);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("\u53D6\u6D88");
		button_5.setBounds(396, 563, 103, 27);
		contentPane.add(button_5);
	}
}
