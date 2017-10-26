package com.server.service;

import com.common.enums.Pagination;
import com.server.app.DepartmentApp;
import com.server.app.LineApp;
import com.server.model.LineModel;

public class LineService {
	private LineApp lineApp = new LineApp();
	private DepartmentApp departmentApp = new DepartmentApp();

	public LineModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		
		return lineApp.getTable(page);
	}

	public LineModel[] getTable() {
		// TODO Auto-generated method stub
		
		return lineApp.getTable();
	}

	public boolean addLine(LineModel lineModel) {
		// TODO Auto-generated method stub
		return lineApp.addLine(lineModel);
	}

}
