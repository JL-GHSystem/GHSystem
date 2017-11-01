package com.clinet.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;

public class Pop1 {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private String[] h1= {"序列","车辆编号","当前所在"};
	private Object[][] data1= {{"1","2015","211"}};
	private String[] h2= {"序号","车辆编号","所属"};
	private Object[][] data2= null;
	DefaultTableModel tablemodel,tablemodel2;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pop1 window = new Pop1();
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
	public Pop1() {
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
		frame.setTitle("\u8F66\u8F86\u9009\u62E9");
		frame.setBounds(700, 210, 571, 572);
		
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 245, 149);
		frame.getContentPane().add(scrollPane);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("\u5609\u5174\u56FD\u9E3F") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("\u57CE\u9645\u516C\u4EA4");
						node_1.add(new DefaultMutableTreeNode("JD1"));
						node_1.add(new DefaultMutableTreeNode("\u5E73\u6E56"));
					add(node_1);
				}
			}
		));
		scrollPane.setViewportView(tree);
		
		JLabel lblNewLabel = new JLabel("\u8F66\u8F86\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(0, 162, 75, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(77, 162, 101, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.setBounds(184, 162, 61, 27);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 193, 245, 228);
		frame.getContentPane().add(scrollPane_1);
		
		tablemodel =new DefaultTableModel(data1, h1);
		table = new JTable(tablemodel);
		table.setEnabled(false);
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setToolTipText("");
		scrollPane_2.setBounds(321, 23, 232, 402);
		frame.getContentPane().add(scrollPane_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u5DF2\u9009\u62E9\u8F66\u8F86");
		scrollPane_2.setColumnHeaderView(lblNewLabel_1);
		
		tablemodel2 =new DefaultTableModel(data2, h2);
		table_1 = new JTable(tablemodel2);
		table_1.setEnabled(false);
		DefaultTableModel tablemodel2 = (DefaultTableModel) table_1.getModel();
	       
        int nL=18;
	    tablemodel2.setRowCount(nL);
		scrollPane_2.setViewportView(table_1);
		
		JButton btnNewButton_1 = new JButton("\u5168\u90E8\u9009\u62E9");
		btnNewButton_1.setBounds(331, 438, 93, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton button = new JButton("\u5168\u90E8\u79FB\u9664");
		button.setBounds(446, 438, 93, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setBounds(446, 485, 93, 27);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u786E\u5B9A");
		button_2.setBounds(331, 485, 93, 27);
		frame.getContentPane().add(button_2);
		
		JLabel label = new JLabel("\u5DF2\u9009\u62E9\u8F66\u8F86");
		label.setBounds(321, 0, 143, 18);
		frame.getContentPane().add(label);
		
	}

}
