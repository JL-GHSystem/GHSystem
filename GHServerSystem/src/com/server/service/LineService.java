package com.server.service;

import com.common.enums.Pagination;
import com.server.app.LineApp;
import com.server.model.LineModel;

public class LineService {
	private LineApp lineApp = new LineApp();

	public LineModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		
		return lineApp.getTable(page);
	}

}
