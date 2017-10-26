package com.server.app;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.dao.DepartmentDao;
import com.server.iDao.IDepartmentDao;
import com.server.model.DepartmentModel;

public class DepartmentApp {
	private IDepartmentDao departmentDao = new DepartmentDao();
	
	public DepartmentModel[] getTree() {
		// TODO Auto-generated method stub
		return departmentDao.selectAllInTree();
	}

	public DepartmentModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<DepartmentModel> departmentModels = departmentDao.select(page);
		DepartmentModel[] departmentModelss = new DepartmentModel[departmentModels.size()];
		departmentModels.toArray(departmentModelss);
		return departmentModelss;
	}

	public boolean addDepartment(DepartmentModel departmentModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = departmentDao.insert(departmentModel);
		return a;
	}
	
}
