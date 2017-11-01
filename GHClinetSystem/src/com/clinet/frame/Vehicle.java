package com.clinet.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreeSelectionModel;

import com.clinet.controller.*;
import com.clinet.view.DepartmentView;


public class Vehicle extends JFrame {
	DepartmentController departmentController = new DepartmentController();
	VehicleController vehicleController = new VehicleController();
	
	public Vehicle() {
		// TODO Auto-generated constructor stub
		JFrame j2=new JFrame("车辆选择");
		j2.setLayout(new FlowLayout());
		
		j2.setSize(600, 620);
		
		
		j2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        j2.setVisible(true);
        
        JPanel big=new JPanel();
        big.setLayout(new GridBagLayout());
        JScrollPane sr=new JScrollPane(departmentController.getDepartmentView(), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc_sr = new GridBagConstraints();
        gbc_sr.insets = new Insets(0, 0, 5, 5);
        gbc_sr.fill = GridBagConstraints.BOTH;
        gbc_sr.anchor = GridBagConstraints.NORTHWEST;
        gbc_sr.gridx = 0;
        gbc_sr.gridy = 1;

        
        
        JTextField jt1=new JTextField();
        jt1.setPreferredSize(new Dimension(200, 25));
        JLabel jl=new JLabel("车辆名称");
        JButton jb1=new JButton("查询");
        JPanel middle=new JPanel();
        middle.setLayout(new FlowLayout());
        middle.add(jl);
        middle.add(jt1);
        middle.add(jb1);
        GridBagConstraints gbc_middle = new GridBagConstraints();
        gbc_middle.fill = GridBagConstraints.BOTH;
        gbc_middle.insets = new Insets(0, 0, 5, 5);
        gbc_middle.gridx = 0;
        gbc_middle.gridy = 2;
        
        
        JScrollPane scrollPaneL = new JScrollPane(vehicleController.getVehicleView());
        
        GridBagConstraints gbc_scrollPaneL=new GridBagConstraints();
        gbc_scrollPaneL.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPaneL.fill = GridBagConstraints.BOTH;
        gbc_scrollPaneL.gridx=0;
        gbc_scrollPaneL.gridy=3;
       
        
        
        big.add(sr, gbc_sr);
        big.add(middle, gbc_middle);
        big.add(scrollPaneL,gbc_scrollPaneL);
        
        
        j2.add(big);

        
	}
	
}
