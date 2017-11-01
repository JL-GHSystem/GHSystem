package com.server.service;

import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.model.IModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IService {
	
	default Pagination dTP(String current, String rows) {
		Pagination page = new Pagination();
		if(Lib.istEmpty(current)) {
			page.setCurrent(Integer.parseInt(current));
		}
		if(Lib.istEmpty(rows)) {
			page.setRows(Integer.parseInt(rows));
		}
		return page;
	}

	default JSONObject pTJ(Pagination p) {
		p.setTotal();
		JSONObject jo = JSONObject.fromObject(p);
		return jo;
	}
	
	public IModel jTB(JSONObject jo);
	
	public IModel[] jTB(JSONArray ja);
	
	
}
