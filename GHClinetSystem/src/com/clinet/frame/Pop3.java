package com.clinet.frame;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Pop3 {

	private JFrame frame;
	private JTextField textField;
	private String[] h1= {"序号","姓名","部门","所在车辆","类型"};
	private String[] h2= {"序号","车辆","部门","状态"};
	private Object[][] data1= null;
	DefaultTableModel tablemodel,tablemodel2;
	private JTable table;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pop3 window = new Pop3();
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
	public Pop3() {
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
		frame.setTitle("\u8BED\u97F3\u5E7F\u64AD");
		frame.setBounds(700, 210, 836, 568);
	
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 237, 149);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("\u5609\u5174\u56FD\u9E3F") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					DefaultMutableTreeNode node_21;
					add(new DefaultMutableTreeNode("\u6D4B\u8BD51"));
					add(new DefaultMutableTreeNode("\u65B0\u8F66\u4E2D\u5BA2"));
					add(new DefaultMutableTreeNode("\u573A\u7AD9\u76D1\u63A7"));
					node_1 = new DefaultMutableTreeNode("\u57CE\u9645\u516C\u4EA4");
						node_1.add(new DefaultMutableTreeNode("JD1"));
						node_1.add(new DefaultMutableTreeNode("平湖"));
					add(node_1);
					node_2 = new DefaultMutableTreeNode("城乡公交");
					node_21 = new DefaultMutableTreeNode("二车队");   
					    node_21.add(new DefaultMutableTreeNode("221"));
					 node_2.add(node_21);
					 add(node_2);
				}
			}
		));
		scrollPane.setViewportView(tree);
		
		JLabel lblNewLabel = new JLabel("\u8F66\u8F86\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(0, 165, 75, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(70, 162, 101, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.setBounds(176, 162, 61, 27);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 193, 237, 228);
		frame.getContentPane().add(scrollPane_1);
		
		tablemodel =new DefaultTableModel(data1, h1);
		table = new JTable(tablemodel);
		table.setEnabled(false);
		DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
	       
        int nL=18;
	    tablemodel.setRowCount(nL);
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(396, 23, 408, 398);
		frame.getContentPane().add(scrollPane_2);
		
		
		tablemodel2 =new DefaultTableModel(data1, h2);
		table_1 = new JTable(tablemodel2);
		table_1.setEnabled(false);
		DefaultTableModel tablemodel2 = (DefaultTableModel) table_1.getModel();
	       
        int nL2=18;
	    tablemodel2.setRowCount(nL2);
		scrollPane_2.setViewportView(table_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u5E7F\u64AD\u5217\u8868");
		lblNewLabel_1.setBounds(396, 0, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("---->");
		btnNewButton_1.setBounds(307, 90, 75, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton button = new JButton("--->>");
		button.setBounds(307, 140, 75, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u5220\u9664");
		button_1.setBounds(307, 186, 75, 27);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u6E05\u7A7A");
		button_2.setBounds(307, 232, 75, 27);
		frame.getContentPane().add(button_2);
		
		JButton btnNewButton_2 = new JButton("\u5F00\u59CB\u5E7F\u64AD");
		btnNewButton_2.setBounds(564, 434, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton button_3 = new JButton("\u505C\u6B62\u5E7F\u64AD");
		button_3.setBounds(691, 434, 113, 27);
		frame.getContentPane().add(button_3);
		
		
	}
	

}
