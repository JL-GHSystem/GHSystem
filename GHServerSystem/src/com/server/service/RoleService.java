package com.server.service;

import com.server.app.RoleApp;
import com.server.model.MenuModel;
import com.server.model.UserGroupModel;

public class RoleService {
	private RoleApp roleApp = new RoleApp();

	public UserGroupModel[] getUserGroupTable() {
		// TODO Auto-generated method stub
		return roleApp.getUserGroupTable();
	}

	public MenuModel[] getMenuTree() {
		// TODO Auto-generated method stub
		return roleApp.getMenuTree();
	}

	public boolean addGroup(UserGroupModel userGroupModel, String[] ids) {
		// TODO Auto-generated method stub
		return roleApp.addGroup(userGroupModel, ids);
	}

	public boolean deleteGroup(String[] gids) {
		// TODO Auto-generated method stub
		return roleApp.deleteGroup(gids);
	}

}
