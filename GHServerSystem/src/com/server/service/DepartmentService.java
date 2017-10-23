package com.server.service;

import com.common.enums.Pagination;
import com.server.app.DepartmentApp;
import com.server.model.DepartmentModel;

public class DepartmentService {
	private DepartmentApp departmentApp = new DepartmentApp();

	public DepartmentModel[] getDepartment() {
		// TODO Auto-generated method stub
		return departmentApp.getDepartment();
	}

	public DepartmentModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		return departmentApp.getTable(page);
	}
	
	
	
}
