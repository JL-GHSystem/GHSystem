package com.server.service;

import com.server.app.UserApp;
import com.server.model.UserModel;

public class UserService implements IService{
	private UserApp userApp = new UserApp();

	public UserModel[] getUser() {
		// TODO Auto-generated method stub
		return userApp.getUser();
	}

	
	
}
