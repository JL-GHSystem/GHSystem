package com.server.iDao;

import java.util.ArrayList;

import com.server.model.UserGroupModel;

public interface IUserGroupDao {

	public UserGroupModel selectByUser(String o_USERID);

	public ArrayList<UserGroupModel> selectInTable();

	public boolean insert(UserGroupModel userGroupModel);

	public UserGroupModel select(UserGroupModel userGroupModel);

	public boolean deleteInUser(String[] gids);

	public boolean deleteInMenu(String[] gids);

	public boolean delete(String[] gids);
	
}
