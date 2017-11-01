package com.server.service;

import com.server.app.SchemeApp;
import com.server.model.FenceModel;
import com.server.model.SchemeModel;

public class SchemeService {
	private SchemeApp schemeApp = new SchemeApp();

	public SchemeModel[] getSchemeInLine(String o_LINECODE) {
		// TODO Auto-generated method stub
		return schemeApp.getSchemeInLine(o_LINECODE);
	}

	public FenceModel[] getFenceInScheme(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		return schemeApp.getFenceInScheme(schemeModel);
	}

	public FenceModel[] getFenceInLine(String o_LineCode) {
		// TODO Auto-generated method stub
		return schemeApp.getFenceInLine(o_LineCode);
	}

	public boolean addscheme(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		return schemeApp.addscheme(schemeModel);
	}

	public boolean updateScheme(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		return schemeApp.updateScheme(schemeModel);
	}
	
	public boolean updateFenceInScheme(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		return schemeApp.updateFenceInScheme(schemeModel);
	}

	public boolean deleteScheme(SchemeModel[] schemeModels) {
		// TODO Auto-generated method stub
		return schemeApp.deleteScheme(schemeModels);
	}


}
