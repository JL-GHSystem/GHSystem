package com.clinet.bean;

import java.util.ArrayList;

public class DepartmentBean implements IBean {
	
	private String id;
	private String name;
	private ArrayList<DepartmentBean> child = new ArrayList<DepartmentBean>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<DepartmentBean> getChild() {
		return child;
	}
	public void setChild(ArrayList<DepartmentBean> child) {
		this.child = child;
	}
	public void setChild(DepartmentBean de) {
		// TODO Auto-generated method stub
		this.child.add(de);
	}	
	
}
