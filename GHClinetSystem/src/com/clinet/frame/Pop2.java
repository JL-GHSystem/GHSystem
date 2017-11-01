package com.clinet.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

public class Pop2 {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private String[] header= {"序号","姓名","部门","所在车辆","类型"};
	private Object[][] data= null;
	private String[] header2= {"序号","姓名","类型"};
	private Object[][] data2= null;
	DefaultTableModel tableModel,tableModel2;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pop2 window = new Pop2();
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
	public Pop2() {
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
		frame.setTitle("\u4EBA\u5458\u9009\u62E9");
		frame.setBounds(700, 210, 571, 572);
		
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 237, 149);
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
		
		JLabel label = new JLabel("\u7C7B\u522B\uFF1A");
		label.setBounds(10, 153, 53, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		label_1.setBounds(10, 184, 53, 18);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setColumns(10);
		textField.setBounds(59, 184, 100, 24);
		frame.getContentPane().add(textField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u9ED1\u540D\u5355");
		chckbxNewCheckBox.setBounds(168, 149, 73, 27);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(59, 153, 100, 24);
		comboBox.addItem("司机");
		comboBox.addItem("乘务员");
		comboBox.setSelectedItem(null);
		frame.getContentPane().add(comboBox);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.setBounds(168, 183, 73, 27);
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 215, 237, 206);
		frame.getContentPane().add(scrollPane_1);
		
		tableModel=new DefaultTableModel(data, header);
		table = new JTable(tableModel);
		table.setEnabled(false);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	       
        int nL=18;
	    tableModel.setRowCount(nL);
		
		scrollPane_1.setViewportView(table);
		
		JLabel label_2 = new JLabel("\u5DF2\u9009\u62E9\u4EBA\u5458");
		label_2.setBounds(378, 0, 123, 18);
		frame.getContentPane().add(label_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(316, 16, 237, 405);
		frame.getContentPane().add(scrollPane_2);
		
		tableModel2=new DefaultTableModel(data2, header2);
		table_1 = new JTable(tableModel2);
		table_1.setEnabled(false);
		DefaultTableModel tableModel2 = (DefaultTableModel) table_1.getModel();
	       
        int nL2=18;
	    tableModel2.setRowCount(nL2);
		scrollPane_2.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("\u5168\u90E8\u9009\u62E9");
		btnNewButton.setBounds(331, 438, 93, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton button_1 = new JButton("\u5168\u90E8\u79FB\u9664");
		button_1.setBounds(446, 438, 93, 27);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u786E\u5B9A");
		button_2.setBounds(331, 478, 93, 27);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u53D6\u6D88");
		button_3.setBounds(446, 478, 93, 27);
		frame.getContentPane().add(button_3);
	}
}
