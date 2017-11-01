package com.clinet.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Pop51 {

	private JFrame frame;
	private JTable table;
    private String[] h1= {"序号","短信内容"};
    private Object[][] data1= {{"1","雪天路滑，请注意驾驶"},{"2","天气炎热，请注意防暑"}};
    DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pop51 window = new Pop51();
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
	public Pop51() {
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
		frame.setBounds(700, 210, 419, 501);
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u77ED\u4FE1\u5185\u5BB9");
		lblNewLabel.setBounds(14, 13, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(24, 44, 351, 76);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.setBounds(53, 133, 70, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u5220\u9664");
		button.setBounds(137, 133, 70, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u4E0A\u79FB");
		button_1.setBounds(221, 133, 70, 27);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u4E0B\u79FB");
		button_2.setBounds(305, 133, 70, 27);
		frame.getContentPane().add(button_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u5E38\u7528\u77ED\u4FE1\u5217\u8868");
		lblNewLabel_1.setBounds(14, 173, 109, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 204, 351, 237);
		frame.getContentPane().add(scrollPane);
		
		tableModel =new DefaultTableModel(data1, h1);
		table = new JTable(tableModel);
		table.setEnabled(false);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	       
        int nL=11;
	    tableModel.setRowCount(nL);

		scrollPane.setViewportView(table);
	}

}
