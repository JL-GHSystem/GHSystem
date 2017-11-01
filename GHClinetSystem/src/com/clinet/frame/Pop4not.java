package com.clinet.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class Pop4not {

	private JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTable table;
	private String[] header= {"序号","时间","类型","车辆",};
	private Object[][] data= null;
	
	DefaultTableModel tablemodel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pop4not window = new Pop4not();
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
	public Pop4not() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u544A\u8B66\u8BBE\u7F6E");
		frame.setBounds(700, 210, 831, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tabbedPane.setBounds(0, 0, 813, 527);
		JPanel p1 = new JPanel();
		tabbedPane.add(p1,"消息");
		p1.setLayout(null);
		
		JButton button = new JButton("\u672A\u8BFB");
		button.setBounds(0, 13, 75, 27);
		p1.add(button);
		
		JButton button_1 = new JButton("\u5168\u90E8\u5DF2\u8BFB");
		button_1.setBounds(89, 13, 93, 27);
		p1.add(button_1);
		
		JButton button_2 = new JButton("\u5DF2\u8BFB");
		button_2.setBounds(196, 13, 85, 27);
		p1.add(button_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 53, 389, 429);
		p1.add(scrollPane);
		
		tablemodel=new DefaultTableModel(data, header);
		table = new JTable(tablemodel);
		table.setEnabled(false);
		DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
	       
        int nL=300;
	    tablemodel.setRowCount(nL);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
	    
		panel.setBounds(426, 55, 382, 427);
		p1.add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setBounds(0, 0, 382, 427);
		panel.add(textArea);
		
		JLabel lblNewLabel = new JLabel("\u6D88\u606F\u8BE6\u7EC6");
		lblNewLabel.setBounds(426, 31, 72, 18);
		p1.add(lblNewLabel);
		JPanel p2 = new JPanel();
	    tabbedPane.add(p2,"设置");
	    p2.setLayout(null);
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(0, 35, 256, 385);
	    p2.add(scrollPane_1);
	    
	    JScrollPane scrollPane_2 = new JScrollPane();
	    scrollPane_2.setBounds(277, 35, 256, 385);
	    p2.add(scrollPane_2);
	    
	    JScrollPane scrollPane_3 = new JScrollPane();
	    scrollPane_3.setBounds(552, 35, 256, 385);
	    p2.add(scrollPane_3);
	    
	    JCheckBox chckbxNewCheckBox = new JCheckBox("\u6EDA\u52A8\u6D88\u606F\u680F");
	    chckbxNewCheckBox.setBounds(10, 9, 133, 27);
	    p2.add(chckbxNewCheckBox);
	    
	    JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\u53F3\u4E0B\u89D2\u5F39\u7A97");
	    chckbxNewCheckBox_1.setBounds(288, 9, 133, 27);
	    p2.add(chckbxNewCheckBox_1);
	    
	    JCheckBox chckbxNewCheckBox_2 = new JCheckBox("\u58F0\u97F3\u62A5\u8B66\uFF08\u53CC\u51FB\u58F0\u97F3\u8BD5\u542C\uFF09");
	    chckbxNewCheckBox_2.setBounds(564, 9, 209, 27);
	    p2.add(chckbxNewCheckBox_2);
		frame.getContentPane().add(tabbedPane);
		
	}
}
