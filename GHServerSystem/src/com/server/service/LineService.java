package com.server.service;

import com.common.enums.Pagination;
import com.server.app.LineApp;
import com.server.model.DepartmentModel;
import com.server.model.IModel;
import com.server.model.LineModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LineService implements IService {
	private LineApp lineApp = new LineApp();
	
	public LineModel[] getTable(Pagination p, LineModel lineModel) {
		// TODO Auto-generated method stub
		return lineApp.getTable(p, lineModel);
	}

	public boolean addLine(LineModel lineModel) {
		// TODO Auto-generated method stub
		return lineApp.addLine(lineModel);
	}

	public boolean deleteLine(String[] ids) {
		// TODO Auto-generated method stub
		return lineApp.deleteLine(ids);
	}

	public boolean updateLine(LineModel lineModel) {
		// TODO Auto-generated method stub
		return lineApp.updateLine(lineModel);
	}

	public LineModel[] getSLTable(Pagination page) {
		// TODO Auto-generated method stub
		return lineApp.getSLTable(page);
	}
	
	@Override
	public LineModel jTB(JSONObject jo) {
		// TODO Auto-generated method stub
		LineModel lineModel = (LineModel) JSONObject.toBean(jo, LineModel.class);
		return lineModel;
	}

	@Override
	public LineModel[] jTB(JSONArray ja) {
		// TODO Auto-generated method stub

		LineModel[] lineModel = (LineModel[]) JSONArray.toArray(ja, LineModel.class);
		return lineModel;
	}

}
