package com.clinet.view;

import javax.swing.JTable;

import com.clinet.bean.VehicleBean;

public class VehicleView extends JTable implements IView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehicleView(VehicleBean vb) {
		// TODO Auto-generated constructor stub
		super(vb.getTableModel());
		
	}
	
	
}
