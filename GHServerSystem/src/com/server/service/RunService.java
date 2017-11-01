package com.server.service;

import com.common.enums.Pagination;
import com.server.app.RunApp;
import com.server.model.RunModel;

public class RunService {
	private RunApp runApp = new RunApp();

	public RunModel[] getTable(Pagination page, RunModel runModel) {
		// TODO Auto-generated method stub
		return runApp.getTable(page, runModel);
	}

}
