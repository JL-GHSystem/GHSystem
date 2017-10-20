package com.server.service;

import com.server.app.MenuApp;
import com.server.model.MenuModel;
import com.server.model.UserModel;

public class MenuService implements IService{
	private MenuApp menuApp = new MenuApp();
	
	public MenuModel[] getMenu(UserModel userModel) {
		return menuApp.getMenu(userModel);
	}

	public MenuModel[] getTable(UserModel userModel) {
		return menuApp.getTable(userModel);
	}
	
	
	
}
