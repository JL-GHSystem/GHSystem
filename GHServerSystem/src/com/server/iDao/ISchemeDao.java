package com.server.iDao;

import java.util.ArrayList;

import com.server.model.SchemeModel;

public interface ISchemeDao {

	public ArrayList<SchemeModel> selectInLine(String o_LINECODE);

	public boolean insert(SchemeModel schemeModel);

	public boolean updateFenceInScheme(SchemeModel schemeModel);

	public boolean delete(SchemeModel[] schemeModels);

	public boolean update(SchemeModel schemeModel);
	
}
