package com.server.app;

import com.server.dao.MenuDao;
import com.server.dao.UserGroupDao;
import com.server.iDao.IMenuDao;
import com.server.iDao.IUserGroupDao;
import com.server.model.MenuModel;
import com.server.model.UserGroupModel;
import com.server.model.UserModel;

public class MenuApp {
	
	private IMenuDao menuDao = new MenuDao();
	private IUserGroupDao userGroupDao = new UserGroupDao();
	
	public MenuModel[] getMenu (UserModel userModel) {
		
		UserGroupModel userGroupModel = userGroupDao.selectByUser(userModel.getO_USERID());
		
		MenuModel[] menuModels = menuDao.selectByGroup(userGroupModel.getO_USERGROUPID());
		
		return menuModels;
	}

	public MenuModel[] getTable (UserModel userModel) {
		// TODO Auto-generated method stub

		UserGroupModel userGroupModel = userGroupDao.selectByUser(userModel.getO_USERID());
		
		MenuModel[] menuModels = menuDao.selectByGroupInTable(userGroupModel.getO_USERGROUPID());
		
		return menuModels;
	}
	
	
	
	
}
