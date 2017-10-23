package com.server.iDao;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.model.DepartmentModel;

public interface IDepartmentDao {

	public DepartmentModel[] selectAll();

	public ArrayList<DepartmentModel> select(Pagination page);

}
