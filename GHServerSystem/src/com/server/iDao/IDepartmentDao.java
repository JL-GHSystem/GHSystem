package com.server.iDao;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.model.DepartmentModel;

public interface IDepartmentDao {

	public DepartmentModel[] selectAllInTree();

	public ArrayList<DepartmentModel> select(Pagination page, DepartmentModel departmentModel);
	public ArrayList<DepartmentModel> select();

	public boolean insert(DepartmentModel departmentModel);

	public boolean delete(String[] ids);

	public boolean update(DepartmentModel departmentModel);


}
