package com.server.service;

import com.common.enums.Pagination;
import com.server.app.DepartmentApp;
import com.server.model.DepartmentModel;

public class DepartmentService {
	private DepartmentApp departmentApp = new DepartmentApp();

	public DepartmentModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		return departmentApp.getTable(page);
	}

	public boolean addDepartment(DepartmentModel departmentModel) {
		// TODO Auto-generated method stub
		return departmentApp.addDepartment(departmentModel);
	}
	
	
	
}
