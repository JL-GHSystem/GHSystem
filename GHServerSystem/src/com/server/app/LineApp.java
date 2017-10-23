package com.server.app;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.dao.LineDao;
import com.server.iDao.ILineDao;
import com.server.model.LineModel;

public class LineApp {
	private ILineDao lineDao = new LineDao();

	public LineModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<LineModel> lineModels = lineDao.select(page);
		LineModel[] lineModelss = new LineModel[lineModels.size()];
		lineModels.toArray(lineModelss);
		
		return lineModelss;
	}

}
