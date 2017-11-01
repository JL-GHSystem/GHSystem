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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class Pop5 {

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
					Pop5 window = new Pop5();
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
	public Pop5() {
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
		frame.setTitle("\u77ED\u4FE1\u7BA1\u7406");
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
		scrollPane_1.setBounds(0, 193, 280, 321);
		frame.getContentPane().add(scrollPane_1);
		
		tablemodel =new DefaultTableModel(data1, h1);
		table = new JTable(tablemodel);
		table.setEnabled(false);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	       
        int nL=18;
	    tableModel.setRowCount(nL);
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(386, 24, 416, 213);
		frame.getContentPane().add(scrollPane_2);
		
		tablemodel2 =new DefaultTableModel(data2, h2);
		table_1 = new JTable(tablemodel2);
		table_1.setEnabled(false);
		DefaultTableModel tableModel2 = (DefaultTableModel) table_1.getModel();
	       
        int nL2=18;
	    tableModel2.setRowCount(nL2);
		
		scrollPane_2.setViewportView(table_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u77ED\u4FE1\u5217\u8868");
		lblNewLabel_1.setBounds(386, 0, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u58F0\u97F3");
		lblNewLabel_2.setBounds(386, 250, 72, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u77ED\u4FE1\u5185\u5BB9");
		lblNewLabel_3.setBounds(386, 313, 72, 18);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u5E38\u7528\u77ED\u4FE1\uFF1A");
		lblNewLabel_4.setBounds(396, 344, 83, 18);
		frame.getContentPane().add(lblNewLabel_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(470, 340, 287, 27);
		comboBox.addItem("雪天路滑，请注意驾驶");
		comboBox.addItem("天气炎热，请注意防暑");
		comboBox.setSelectedItem(null);
		frame.getContentPane().add(comboBox);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pop51 t5_1 = new Pop51();
				t5_1.getFrame().setVisible(true);
			}
		});
		button.setBounds(765, 340, 45, 25);
		frame.getContentPane().add(button);
		
		JLabel label = new JLabel("\u77ED\u4FE1\u5185\u5BB9\uFF1A");
		label.setBounds(396, 387, 79, 18);
		frame.getContentPane().add(label);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(474, 386, 331, 82);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton_1 = new JButton("\u53D1\u9001\u5931\u8D25\u7684\u77ED\u4FE1\u91CD\u65B0\u53D1\u9001");
		btnNewButton_1.setBounds(301, 485, 198, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u6E05\u7A7A\u77ED\u4FE1\u5185\u5BB9");
		btnNewButton_2.setBounds(507, 485, 124, 27);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u6DFB\u52A0\u77ED\u4FE1");
		btnNewButton_3.setBounds(640, 485, 98, 27);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("\u53D1\u9001");
		btnNewButton_4.setBounds(748, 485, 63, 27);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("---->");
		btnNewButton_5.setBounds(307, 80, 75, 27);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton button_1 = new JButton("--->>");
		button_1.setBounds(307, 142, 75, 27);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.setBounds(307, 197, 75, 27);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u6E05\u7A7A");
		button_3.setBounds(307, 250, 75, 27);
		frame.getContentPane().add(button_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u53F8\u673A\u5587\u53ED");
		chckbxNewCheckBox.setBounds(470, 275, 98, 27);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox checkBox = new JCheckBox("\u8F66\u5185\u5587\u53ED");
		checkBox.setBounds(573, 275, 98, 27);
		frame.getContentPane().add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("\u8F66\u5916\u5587\u53ED");
		checkBox_1.setBounds(677, 275, 113, 27);
		frame.getContentPane().add(checkBox_1);
		
		tablemodel2 =new DefaultTableModel(data2, h2);
	       
	    tablemodel2.setRowCount(nL);
	}
}
