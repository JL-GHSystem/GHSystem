package com.server.service;

import com.common.enums.Pagination;
import com.server.app.FenceApp;
import com.server.model.FenceModel;

public class FenceService {
	private FenceApp fenceApp = new FenceApp();

	public FenceModel[] getTable(Pagination page, String search) {
		// TODO Auto-generated method stub
		return fenceApp.getTable(page, search);
	}

	public FenceModel[] getTableInLine(Pagination page, String o_LineId) {
		// TODO Auto-generated method stub
		return fenceApp.getTableInLine(page, o_LineId);
	}

	public FenceModel[] getTableInLine(Pagination page) {
		// TODO Auto-generated method stub
		return fenceApp.getTableInLine(page);
	}

	public FenceModel[] getTableNotInLine(Pagination page, String o_LineId, String o_Fencename) {
		// TODO Auto-generated method stub
		return fenceApp.getTableNotInLine(page, o_LineId, o_Fencename);
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

	public boolean unbindFence(String ids, String fid) {
		// TODO Auto-generated method stub
		return fenceApp.unbindFence(ids, fid);
	}
	
	public boolean bindFence(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		return fenceApp.bindFence(fenceModel);
	}

	public FenceModel getFenceInLine(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		return fenceApp.getFenceInLine(fenceModel);
	}

	public boolean updateFenceInLine(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		return fenceApp.updateFenceInLine(fenceModel);
	}



}
