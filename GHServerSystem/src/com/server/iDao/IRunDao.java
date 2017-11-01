package com.server.iDao;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.model.RunModel;

public interface IRunDao {

	public ArrayList<RunModel> select(Pagination page, RunModel runModel);

}
