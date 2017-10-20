package com.server.iDao;

import com.server.model.UserGroupModel;

public interface IUserGroupDao {

	UserGroupModel selectByUser(String o_USERID);
	
	
}
