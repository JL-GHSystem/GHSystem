package com.server.service;

import com.common.enums.Pagination;
import com.server.app.DepartmentApp;
import com.server.model.DepartmentModel;
import com.server.model.IModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DepartmentService implements IService{
	private DepartmentApp departmentApp = new DepartmentApp();

	public DepartmentModel[] getTable(Pagination page,DepartmentModel departmentModel) {
		// TODO Auto-generated method stub
		return departmentApp.getTable(page, departmentModel);
	}
	
	public DepartmentModel[] getAll() {
		// TODO Auto-generated method stub
		return departmentApp.getAll();
	}

	public boolean addDepartment(DepartmentModel departmentModel) {
		// TODO Auto-generated method stub
		return departmentApp.addDepartment(departmentModel);
	}

	public boolean deleteDepartment(String[] ids) {
		// TODO Auto-generated method stub

		return departmentApp.deleteDepartment(ids);
	}	

	public boolean updateDepartment(DepartmentModel departmentModel) {
		// TODO Auto-generated method stub
		return departmentApp.updateDepartment(departmentModel);
	}
	
	@Override
	public DepartmentModel jTB(JSONObject jo) {
		// TODO Auto-generated method stub
		DepartmentModel department = (DepartmentModel) JSONObject.toBean(jo, DepartmentModel.class);
		return department;
	}

	@Override
	public DepartmentModel[] jTB(JSONArray ja) {
		// TODO Auto-generated method stub

		DepartmentModel[] department = (DepartmentModel[]) JSONArray.toArray(ja, DepartmentModel.class);
		return department;
	}
	
	
}
