package com.server.app;

import com.server.dao.UserDao;
import com.server.iDao.IUserDao;
import com.server.model.UserModel;

public class UserApp {
	private IUserDao userDao = new UserDao();

	public UserModel[] getUser() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

}
