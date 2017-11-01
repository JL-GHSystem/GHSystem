package com.server.service;

import com.server.app.UserApp;
import com.server.model.IModel;
import com.server.model.UserModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserService implements IService{
	private UserApp userApp = new UserApp();

	public UserModel[] getUser() {
		// TODO Auto-generated method stub
		return userApp.getUser();
	}

	@Override
	public IModel jTB(JSONObject jo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModel[] jTB(JSONArray ja) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
