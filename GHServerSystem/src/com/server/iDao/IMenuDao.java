package com.server.iDao;

import java.util.ArrayList;

import com.server.model.MenuModel;

public interface IMenuDao {
	
	public ArrayList<MenuModel> selectByGroup(String o_USERGROUPID);
	public ArrayList<MenuModel> selectByUser(String o_USERID);	

	public MenuModel[] selectByGroupInTable();

	public boolean insert(MenuModel menuModel);
	
	public MenuModel select(MenuModel menuModel);

	public boolean insert(String o_MENUID, String o_USERID);

	
}
