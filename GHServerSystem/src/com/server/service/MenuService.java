package com.server.service;

import com.server.app.MenuApp;
import com.server.model.MenuModel;
import com.server.model.UserModel;

public class MenuService implements IService{
	private MenuApp menuApp = new MenuApp();
	
	public MenuModel[] getMenu(UserModel userModel) {
		return menuApp.getMenu(userModel);
	}

	public MenuModel[] getTable() {
		return menuApp.getTable();
	}

	public boolean addMenu(MenuModel menuModel,UserModel userModel) {
		// TODO Auto-generated method stub
		return menuApp.addMenu(menuModel, userModel);
	}

	public boolean deleteMenu(MenuModel menuModel) {
		// TODO Auto-generated method stub
		return menuApp.deleteMenu(menuModel);
	}

	public boolean updateMenu(MenuModel menuModel) {
		// TODO Auto-generated method stub
		return menuApp.updateMenu(menuModel);
	}
	
}
