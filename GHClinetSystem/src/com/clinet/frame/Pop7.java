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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Pop7 {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private String[] h1= {"序号", "车辆编号", "当前所在", "司机", "状态","乘务","乘务","乘务"};
	private Object[][] data1= null;
	private String[] h2= {"序号","车辆","部门","状态","短信内容","声音"};
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
					Pop7 window = new Pop7();
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
	public Pop7() {
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
		frame.setTitle("\u5207\u6362\u7EBF\u8DEF");
		frame.setBounds(700, 210, 834, 574);
		
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 280, 149);
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
		textField.setBounds(77, 162, 136, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.setBounds(219, 162, 61, 27);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 193, 280, 270);
		frame.getContentPane().add(scrollPane_1);
		
		tablemodel =new DefaultTableModel(data1, h1);
		table = new JTable(tablemodel);
		table.setEnabled(false);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	       
        int nL=18;
	    tableModel.setRowCount(nL);
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(386, 24, 416, 330);
		frame.getContentPane().add(scrollPane_2);
		
		tablemodel2 =new DefaultTableModel(data2, h2);
		table_1 = new JTable(tablemodel2);
		table_1.setEnabled(false);
		DefaultTableModel tableModel2 = (DefaultTableModel) table_1.getModel();
	       
        int nL2=18;
	    tableModel2.setRowCount(nL2);
		
		scrollPane_2.setViewportView(table_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u5207\u6362\u5217\u8868");
		lblNewLabel_1.setBounds(386, 0, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7EBF\u8DEF\u9009\u62E9");
		lblNewLabel_2.setBounds(386, 367, 72, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u8981\u5207\u6362\u7684\u7EBF\u8DEF");
		lblNewLabel_3.setBounds(386, 398, 99, 18);
		frame.getContentPane().add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setBounds(486, 395, 269, 24);
		comboBox.addItem("测试1");
		comboBox.addItem("111");
		comboBox.setSelectedItem(null);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton_1 = new JButton("\u5207\u6362\u5931\u8D25\u7684\u91CD\u65B0\u5207\u6362");
		btnNewButton_1.setBounds(381, 469, 167, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u6DFB\u52A0\u8DEF\u7EBF");
		btnNewButton_2.setBounds(562, 469, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u5207\u6362");
		btnNewButton_3.setBounds(689, 469, 113, 27);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("---->");
		btnNewButton_4.setBounds(307, 80, 75, 27);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton button = new JButton("--->>");
		button.setBounds(307, 122, 75, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u5220\u9664");
		button_1.setBounds(307, 161, 75, 27);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u6E05\u7A7A");
		button_2.setBounds(307, 201, 75, 27);
		frame.getContentPane().add(button_2);
	}

}
