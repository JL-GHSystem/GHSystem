package com.clinet.bean;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class VehicleBean implements IBean{
	
	public static Vector<Object> title = new Vector<Object>();
	
	private Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
	private Vector<Object> cols = new Vector<Object>();
	
	public VehicleBean() {
		// TODO Auto-generated constructor stub
		title.addElement("���");
		title.addElement("�������");
		title.addElement("��ǰ����");
		title.addElement("˾��");
		title.addElement("״̬");
		title.addElement("����Ա");
		title.addElement("����Ա");
		title.addElement("����Ա");
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<8; j++) {
				cols.addElement(i);
				cols.addElement(j * 10000);
				cols.addElement("221");
				cols.addElement("�¹�");
				cols.addElement("����Ӫ");
				
			}
			rows.addElement(cols);
		}
	}
	
	public DefaultTableModel getTableModel() {
		
		DefaultTableModel dtm = new DefaultTableModel(rows, title);
		
		return dtm;
	}
}
