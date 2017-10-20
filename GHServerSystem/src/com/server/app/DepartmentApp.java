package com.server.app;

import com.server.dao.DepartmentDao;
import com.server.iDao.IDepartmentDao;
import com.server.model.DepartmentModel;

public class DepartmentApp {
	private IDepartmentDao departmentDao = new DepartmentDao();
	
	public DepartmentModel[] getDepartment() {
		// TODO Auto-generated method stub
		return departmentDao.selectAll();
	}
	
}
