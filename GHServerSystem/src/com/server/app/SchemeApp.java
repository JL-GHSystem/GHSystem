package com.server.app;

import java.util.ArrayList;

import com.server.dao.FenceDao;
import com.server.dao.SchemeDao;
import com.server.iDao.IFenceDao;
import com.server.iDao.ISchemeDao;
import com.server.model.FenceModel;
import com.server.model.SchemeModel;

public class SchemeApp {
	private ISchemeDao schemeDao = new SchemeDao();
	private IFenceDao fenceDao = new FenceDao();

	public SchemeModel[] getSchemeInLine(String o_LINECODE) {
		// TODO Auto-generated method stub
		ArrayList<SchemeModel> schemeModel = schemeDao.selectInLine(o_LINECODE);
		SchemeModel[] schemeModels = new SchemeModel[schemeModel.size()];
		schemeModel.toArray(schemeModels);
		return schemeModels;
	}

	public FenceModel[] getFenceInScheme(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		ArrayList<FenceModel> fenceModels = fenceDao.selectByScheme(schemeModel);
		FenceModel[] fenceModela = new FenceModel[fenceModels.size()];
		fenceModels.toArray(fenceModela);
		return fenceModela;
	}

	public FenceModel[] getFenceInLine(String o_LineCode) {
		// TODO Auto-generated method stub
		ArrayList<FenceModel> fenceModels = fenceDao.selectByLine(o_LineCode);
		FenceModel[] fenceModel = new FenceModel[fenceModels.size()];
		fenceModels.toArray(fenceModel);
		return fenceModel;
	}

	public boolean addscheme(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = schemeDao.insert(schemeModel);
		return a;
	}

	public boolean updateScheme(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = schemeDao.update(schemeModel);
		return a;
	}

	public boolean updateFenceInScheme(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = schemeDao.updateFenceInScheme(schemeModel);
		return a;
	}

	public boolean deleteScheme(SchemeModel[] schemeModels) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = schemeDao.delete(schemeModels);
		return a;
	}
	
	
}
