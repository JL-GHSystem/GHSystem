package com.server.service;

import com.server.app.DepartmentApp;
import com.server.model.DepartmentModel;

public class DepartmentService {
	private DepartmentApp departmentApp = new DepartmentApp();

	public DepartmentModel[] getDepartment() {
		// TODO Auto-generated method stub
		return departmentApp.getDepartment();
	}
	
	
	
}
