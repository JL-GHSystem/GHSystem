package com.server.app;

import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.fabric.xmlrpc.base.Array;
import com.server.dao.MenuDao;
import com.server.dao.UserDao;
import com.server.dao.UserGroupDao;
import com.server.iDao.IMenuDao;
import com.server.iDao.IUserDao;
import com.server.iDao.IUserGroupDao;
import com.server.model.MenuModel;
import com.server.model.UserGroupModel;
import com.server.model.UserModel;

public class MenuApp {
	
	private IMenuDao menuDao = new MenuDao();
	private IUserGroupDao userGroupDao = new UserGroupDao();
	
	public MenuModel[] getMenu (UserModel userModel) {
		
		UserGroupModel userGroupModel = userGroupDao.selectByUser(userModel.getO_USERID());
		
		ArrayList<MenuModel> menuModels = menuDao.selectByGroup(userGroupModel.getO_USERGROUPID());
		
		ArrayList<MenuModel> menuModelss = menuDao.selectByUser(userModel.getO_USERID());
		
		ArrayList<MenuModel> menu = new ArrayList<MenuModel>();
		
		Iterator<MenuModel> it = menuModels.iterator();
		while(it.hasNext()) {
			menu.add(it.next());
		}
		it = menuModelss.iterator();
		while(it.hasNext()) {
			menu.add(it.next());
		}
		
		return MenuModel.toTree(menu);
	}

	public MenuModel[] getTable () {
		// TODO Auto-generated method stub
		
		MenuModel[] menuModels = menuDao.selectAllInTable();
		
		return menuModels;
	}

	public boolean addMenu(MenuModel menuModel, UserModel userModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = menuDao.insert(menuModel);
		
		menuModel = menuDao.select(menuModel);

		a = menuDao.insert(menuModel.getO_MENUID(), userModel.getO_USERID());
		
		return a;
	}

	public boolean deleteMenu(MenuModel menuModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = menuDao.deleteByUser(menuModel.getO_MENUID());
		
		a = menuDao.deleteByUserGroup(menuModel.getO_MENUID());
		
		a = menuDao.delete(menuModel.getO_MENUID());
		
		return a;
	}

	public boolean updateMenu(MenuModel menuModel) {
		// TODO Auto-generated method stub
		return menuDao.update(menuModel);
	}
	
}
