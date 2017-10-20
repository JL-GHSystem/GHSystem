package com.server.iDao;

import com.server.model.MenuModel;

public interface IMenuDao {
	
	public MenuModel[] selectByGroup(String o_USERGROUPID);

	public MenuModel[] selectByGroupInTable();
	
	
	
	
}
