package com.server.app;

import java.util.ArrayList;

import com.common.lib.Lib;
import com.server.dao.MenuDao;
import com.server.dao.UserGroupDao;
import com.server.iDao.IMenuDao;
import com.server.iDao.IUserGroupDao;
import com.server.model.MenuModel;
import com.server.model.UserGroupModel;

public class RoleApp {
	private IMenuDao menuDao = new MenuDao();
	private IUserGroupDao userGroupDao = new UserGroupDao();

	public UserGroupModel[] getUserGroupTable() {
		// TODO Auto-generated method stub
		ArrayList<UserGroupModel> userGroupModels = userGroupDao.selectInTable();
		UserGroupModel[] userGroupModelss = new UserGroupModel[userGroupModels.size()];
		userGroupModels.toArray(userGroupModelss);
		
		return userGroupModelss;
	}

	public MenuModel[] getMenuTree() {
		// TODO Auto-generated method stub
		MenuModel[] menuModels = menuDao.selectAllInTree();
		return menuModels;
	}

	public boolean addGroup(UserGroupModel userGroupModel, String[] ids) {
		// TODO Auto-generated method stub
		boolean a = userGroupDao.insert(userGroupModel);
		
		userGroupModel = userGroupDao.select(userGroupModel);
		
		if(Lib.istEmpty(ids)) {
			a = menuDao.insertByGroup(userGroupModel.getO_USERGROUPID(), ids);
		}
		
		return a;
	}

	public boolean deleteGroup(String[] gids) {
		// TODO Auto-generated method stub
		boolean a = userGroupDao.deleteInUser(gids);

		a = userGroupDao.deleteInMenu(gids);

		a = userGroupDao.delete(gids);
		
		return a;
	}

}
