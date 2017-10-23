package com.server.app;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.dao.DepartmentDao;
import com.server.iDao.IDepartmentDao;
import com.server.model.DepartmentModel;

public class DepartmentApp {
	private IDepartmentDao departmentDao = new DepartmentDao();
	
	public DepartmentModel[] getDepartment() {
		// TODO Auto-generated method stub
		return departmentDao.selectAll();
	}

	public DepartmentModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<DepartmentModel> departmentModels = departmentDao.select(page);
		DepartmentModel[] departmentModelss = new DepartmentModel[departmentModels.size()];
		departmentModels.toArray(departmentModelss);
		return departmentModelss;
	}
	
}
