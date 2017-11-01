package com.clinet.frame;

import java.awt.EventQueue;
import javax.swing.JFrame;  
import javax.swing.JScrollPane;  
import javax.swing.JTree;  
import javax.swing.tree.DefaultTreeModel; 

import javax.swing.JFrame;

public class Pop6 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pop6 window = new Pop6();
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
	public Pop6() {
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
		frame.setBounds(700, 210, 422, 555);
		
		
		JTree tree = new JTree();  
        CheckBoxTreeNode rootNode = new CheckBoxTreeNode("root");  
        CheckBoxTreeNode node1 = new CheckBoxTreeNode("node_1");  
        CheckBoxTreeNode node1_1 = new CheckBoxTreeNode("node_1_1");  
        CheckBoxTreeNode node1_2 = new CheckBoxTreeNode("node_1_2");  
        CheckBoxTreeNode node1_3 = new CheckBoxTreeNode("node_1_3");  
        node1.add(node1_1);  
        node1.add(node1_2);  
        node1.add(node1_3);  
        CheckBoxTreeNode node2 = new CheckBoxTreeNode("node_2");  
        CheckBoxTreeNode node2_1 = new CheckBoxTreeNode("node_2_1");  
        CheckBoxTreeNode node2_2 = new CheckBoxTreeNode("node_2_2");  
        node2.add(node2_1);  
        node2.add(node2_2);  
        rootNode.add(node1);  
        rootNode.add(node2);  
        DefaultTreeModel model = new DefaultTreeModel(rootNode);  
        tree.addMouseListener(new CheckBoxTreeNodeSelectionListener());  
        tree.setModel(model);  
        tree.setCellRenderer(new CheckBoxTreeCellRenderer());  
        JScrollPane scroll = new JScrollPane(tree);  
        scroll.setBounds(0, 0, 300, 320);  
        frame.getContentPane().add(scroll);  
          
      
        frame.setVisible(true);  
	}

}
