package com.server.app;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.dao.FenceDao;
import com.server.iDao.IFenceDao;
import com.server.model.FenceModel;

public class FenceApp {
	private IFenceDao fenceDao = new FenceDao();
	
	public FenceModel[] getTable(Pagination page) {

		ArrayList<FenceModel> fenceModels = fenceDao.select(page);
		FenceModel[] fenceModelss = new FenceModel[fenceModels.size()];
		fenceModels.toArray(fenceModelss);
		return fenceModelss;
	}

	public FenceModel getFence(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		
		fenceModel = fenceDao.selectByNode(fenceModel);
		
		return fenceModel;
	}

	public boolean addFence(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = fenceDao.insert(fenceModel);
		fenceModel = fenceDao.select(fenceModel);
		
		a = fenceDao.insert(fenceModel.getO_FENCEID(), fenceModel.getF_FENCENODES());
		
		return a;
	}

	public boolean deleteFence(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = fenceDao.deleteByNode(fenceModel.getO_FENCEID());
		a = fenceDao.delete(fenceModel.getO_FENCEID());
		
		return a;
	}

	public boolean updateFence(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = fenceDao.deleteByNode(fenceModel.getO_FENCEID());
		a = fenceDao.insert(fenceModel.getO_FENCEID(), fenceModel.getF_FENCENODES());
		
		a = fenceDao.update(fenceModel);
		
		return a;
	}
	
	
}
