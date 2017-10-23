package com.server.service;

import com.common.enums.Pagination;
import com.server.app.FenceApp;
import com.server.model.FenceModel;

public class FenceService {
	private FenceApp fenceApp = new FenceApp();

	public FenceModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		return fenceApp.getTable(page);
	}

	public FenceModel getFence(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		return fenceApp.getFence(fenceModel);
	}

	public boolean addFence(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		return fenceApp.addFence(fenceModel);
	}

	public boolean deleteFence(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		return fenceApp.deleteFence(fenceModel);
	}

	public boolean updateFence(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		return fenceApp.updateFence(fenceModel);
	}

}
