package com.server.app;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.dao.RunDao;
import com.server.iDao.IRunDao;
import com.server.model.RunModel;

public class RunApp {
	private IRunDao runDao = new RunDao();

	public RunModel[] getTable(Pagination page, RunModel runModel) {
		// TODO Auto-generated method stub
		ArrayList<RunModel> runModels = runDao.select(page, runModel);
		RunModel[] runModelss = new RunModel[runModels.size()];
		runModels.toArray(runModelss);
		return runModelss;
	}

}
