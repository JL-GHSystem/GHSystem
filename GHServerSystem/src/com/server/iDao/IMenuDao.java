package com.server.iDao;

import java.util.ArrayList;

import com.server.model.MenuModel;

public interface IMenuDao {
	
	public ArrayList<MenuModel> selectByGroup(String o_USERGROUPID);
	public ArrayList<MenuModel> selectByUser(String o_USERID);	

	public MenuModel[] selectAllInTable();

	public MenuModel[] selectAllInTree();
	
	public MenuModel select(MenuModel menuModel);

	public boolean insert(MenuModel menuModel);
	public boolean insert(String o_MENUID, String o_USERID);
	public boolean insertByGroup(String o_USERGROUPID, String[] ids);
	
	public boolean deleteByUser(String o_MENUID);
	public boolean deleteByUserGroup(String o_MENUID);
	public boolean delete(String o_MENUID);
	
	public boolean update(MenuModel menuModel);

	
}
