package com.clinet.controller;


import java.util.Iterator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.clinet.bean.*;
import com.clinet.view.*;

public class DepartmentController implements IController{
	private DepartmentBean departmentBean = new DepartmentBean();
	private DepartmentView departmentView;
	
	public DepartmentController() {
		// TODO Auto-generated constructor stub
		departmentBean.setId("0");
		departmentBean.setName("¼ÎÐË¹úºè");

		for(int i=0; i<3; i++) {
			DepartmentBean de = new DepartmentBean();
			de.setId(departmentBean.getId() + i);
			de.setName(departmentBean.getName() + (i*10));
			departmentBean.setChild(de);
		}
		
		this.bind();
		
	}
	
	public DepartmentController(DepartmentBean departmentBean) {
		// TODO Auto-generated constructor stub
		this.departmentBean = departmentBean;
		
		this.bind();
	}
	
	@Override
	public void bind() {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(departmentBean.getName());
		
		Iterator<DepartmentBean> it = departmentBean.getChild().iterator();
		while(it.hasNext()) {
			DepartmentBean db = it.next();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(db.getName());
			dmtn.add(node);
		}
		
		departmentView = new DepartmentView(dmtn);
	}

	
	
	
	
	
	public DepartmentBean getDepartmentBean() {
		return departmentBean;
	}

	public void setDepartmentBean(DepartmentBean departmentBean) {
		this.departmentBean = departmentBean;
	}

	public DepartmentView getDepartmentView() {
		return departmentView;
	}

	public void setDepartmentView(DepartmentView departmentView) {
		this.departmentView = departmentView;
	}
	
}
